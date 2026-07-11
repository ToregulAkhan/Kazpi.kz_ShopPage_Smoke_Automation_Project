# Kaspi.kz Shop Page Smoke Automation Project

Автотесты для проверки интернет-магазина kaspi.kz **глазами пользователя**:
навигация по категориям, поиск, фильтры, сортировка, карточка товара.
Стек: **Java + Selenium WebDriver + TestNG + Maven**, паттерн **Page Object Model**.

> Этот README описывает классы такими, какие они есть в проекте сейчас —
> по мере добавления новых страниц/тестов дополняй соответствующие разделы.

## Структура пакетов

```
kaspi_shop/
├── base/
│   ├── BasePage.java
│   └── BaseTest.java
├── driver/
│   └── DriverManager.java
├── utils/
│   └── WaitUtils.java
├── constants/
│   ├── Urls.java
│   └── Locator.java
├── pages/
│   ├── HomePage.java
│   ├── SearchPage.java
│   ├── CategoryPage.java
│   ├── ProductPage.java
│   └── CartPage.java
└── (тесты по разделам)
    ├── home/
    │   └── HomePageTest.java
    ├── search/
    │   └── ... (тесты поиска)
    ├── category/
    │   └── CategorySortTest.java
    └── filters/
        └── ... (тесты фильтров)
```

## Классы фреймворка (`base`, `driver`, `utils`, `constants`)

### `driver.DriverManager`
Создаёт и закрывает Chrome через `static WebDriver` (без `ThreadLocal` — тесты
пока запускаются последовательно, не параллельно).

- `initDriver()` — создаёт `ChromeDriver`, максимизирует окно. Вызывать первым.
- `getDriver()` — возвращает уже созданный драйвер.
- `quitDriver()` — закрывает браузер и обнуляет ссылку.

Порядок вызова важен: `initDriver()` → `getDriver()`. Без первого вызова
второй вернёт `null`.

### `base.BasePage`
Родитель для всех Page-классов (`HomePage`, `SearchPage`, `CategoryPage`,
`ProductPage`, `CartPage`). Хранит `driver` и `waitUtils`, даёт общие действия
поверх `WaitUtils`, чтобы страницы не работали с Selenium API напрямую.

| Метод | Что делает |
|---|---|
| `click(By)` | ждёт кликабельности элемента и кликает |
| `getText(By)` | ждёт видимости и возвращает текст |
| `getElement(By)` | ждёт видимости, возвращает `WebElement` |
| `getVisibleAll(By)` | ждёт, что **все** элементы по локатору видимы, возвращает список |
| `getPresentAll(By)` | ждёт **присутствия** в DOM (без требования видимости) — нужен для элементов, скрытых стилями (например, следующие слайды каруселей) |
| `isDisplayed(By)` | безопасная проверка видимости, `false` вместо исключения |
| `isMoreDisplayed(By)` | проверяет, что элемент с индексом `[1]` (второй по счёту) виден — используется, когда нужно убедиться, что результатов **больше одного** |
| `refreshed(By)` | ждёт, пока элемент "обновится" (см. `WaitUtils.waitRefreshed`) — актуально после действий, которые вызывают AJAX-обновление части страницы (сортировка, фильтры) |
| `clearAndType(By, String)` | очищает поле и вводит текст |
| `clearAndTypeAndEntry(By, String)` | то же самое + `Keys.ENTER` |
| `invisible(By)` | ждёт, пока элемент исчезнет (например, лоадер) |
| `getValue(By)` | возвращает атрибут `value` элемента (для `<input>`) |
| `getCurrentUrl()` / `getTitle()` | текущий URL / title страницы |

> ⚠️ **Важно при добавлении новых методов**: внутри метода `X` вызывай
> `waitUtils.someMethod(...)`, а не `X(...)` саму себя — иначе получишь
> `StackOverflowError` (уже наступали на эти грабли с `getText`).

### `base.BaseTest`
Родитель для всех тестовых классов. `@BeforeMethod` создаёт браузер и
`HomePage`, `@AfterMethod` закрывает браузер — так каждый `@Test` стартует
с чистого состояния.

```java
@BeforeMethod
public void setUp() {
    DriverManager.initDriver();
    driver = DriverManager.getDriver();
    homePage = new HomePage(driver);
}

@AfterMethod
public void tearDown() {
    DriverManager.quitDriver();
}
```

### `utils.WaitUtils`
Обёртка над `WebDriverWait` / `ExpectedConditions`. Один экземпляр создаётся
на каждый Page-объект (через конструктор `BasePage`).

| Метод | `ExpectedConditions` внутри | Когда использовать |
|---|---|---|
| `waitVisible(By)` | `visibilityOfElementLocated` | обычный случай — элемент должен быть виден |
| `waitClickable(By)` | `elementToBeClickable` | перед реальным вводом/кликом, когда просто видимости мало (см. историю с полем поиска — элемент был виден, но JS ещё не навесил обработчик) |
| `waitVisibleAll(By)` | `visibilityOfAllElementsLocatedBy` | ждать, что **все** найденные элементы видимы одновременно — не подходит для каруселей/слайдеров, где часть элементов скрыта на соседних слайдах |
| `waitPresentAll(By)` | `presenceOfAllElementsLocatedBy` | элементы есть в DOM, но не обязательно видимы (карусели, скрытые табы) |
| `waitRefreshed(By)` | обновление конкретного элемента | после действий, вызывающих частичную перерисовку страницы (сортировка, фильтр) |
| `waitInvisible(By)` | `invisibilityOfElementLocated` | дождаться исчезновения лоадера/спиннера |

### `constants.Urls`
Централизованные URL разделов сайта (`BASE_URL`, ссылки на категории и т.д.),
чтобы не хардкодить строки в Page-классах.

### `constants.Locator`
Централизованное хранилище локаторов (`By`), которые используются в
нескольких тестах/страницах — например `Locator.SEARCH_INPUT`,
`Locator.ACTUAL_SELECT_LIST`, `Locator.ITEM_CARD`, `Locator.POPULAR_LIST`,
`Locator.NEW_LIST`, `Locator.CHEEP_PRICE_LIST`, `Locator.EXPENSIVE_PRICE_LIST`,
`Locator.TOP_RATING_LIST`.

> Локаторы уточнены через реальный DOM сайта (DevTools), не выдуманы —
> при изменении вёрстки правь их именно здесь, один раз для всех тестов.

## Page-классы (`pages`)

### `HomePage`
Главная страница: поиск, меню категорий, переход к каталогу.

- `open()` — переход на `Urls.BASE_URL`.
- `searchPage()` / поиск — переход к `SearchPage` через ввод запроса.
- `categoryPageOpen()` — переход к `CategoryPage`.
- Категории на главной рендерятся **каруселью** (`slider__slide`) — вторая
  и последующие группы категорий физически появляются в DOM только после
  клика по стрелке `[data-test-id='categories-next-slide']` (lazy-render).
  Для сбора **всех** категорий нужно кликать по стрелке в цикле и на каждой
  итерации собирать видимые в этот момент элементы `.category-item__title`
  в `Set`, чтобы не было дублей.

### `SearchPage`
Результаты поиска.

- `inputToSearch(String)` — вводит запрос в `Locator.SEARCH_INPUT` и жмёт Enter.

### `CategoryPage`
Каталог категории: список товаров, сортировка, фильтры.

- Сортировка — открыть список сортировки (`Locator.ACTUAL_SELECT_LIST`),
  кликнуть нужный пункт (`POPULAR_LIST` / `NEW_LIST` / `CHEEP_PRICE_LIST` /
  `EXPENSIVE_PRICE_LIST` / `TOP_RATING_LIST`), дождаться обновления списка
  товаров (`refreshedMore(Locator.ITEM_CARD)`).
- Фильтры — клик по значению фильтра (например, ценовой диапазон, бренд),
  дождаться появления соответствующей строки в `.filters__filter-row._active`.

### `ProductPage`
Карточка товара — открывается кликом по карточке из `SearchPage`/`CategoryPage`.

### `CartPage`
Корзина — минимальный класс, расширяется по мере необходимости.

## Тестовые классы

### `home.HomePageTest`
Проверки главной страницы: заголовок, категории (включая карусель), город.

### `category.CategorySortTest`
Проверка всех видов сортировки в категории. Общая логика вынесена в приватный
метод `entryToListItem(By locator)`, который: открывает категорию → открывает
список сортировки → выбирает нужный пункт → дожидается обновления списка
товаров → открывает первую карточку. Каждый `@Test` вызывает этот метод с
конкретным локатором сортировки и затем проверяет специфичное для данной
сортировки условие (например, для "Популярные" — что рейтинг не пустой; для
"Новинки" — что блока рейтинга нет вообще, так как у новых товаров ещё нет
отзывов).

### `search` / `filters`
Тесты поиска и фильтрации (по цене, бренду, цвету и т.д.) — используют
`CategoryPage.applyPriceFilter(...)` / фильтр по чекбоксу с проверкой, что
список активных фильтров (`.filters__filter-row._active`) обновился.

## Частые проблемы, с которыми уже сталкивались (и как решали)

| Симптом | Причина | Решение |
|---|---|---|
| `StackOverflowError` в `getText`/аналогичном методе | метод вызывал сам себя вместо `waitUtils.*` | всегда обращаться к `waitUtils`/`WebElement` внутри, не к себе |
| `NullPointerException: this.homePage is null` | поле `homePage` переобъявлено в тестовом классе (shadowing), затеняет унаследованное из `BaseTest` | не объявлять заново поля, уже существующие в родителе |
| `RuntimeException: config.properties не найден` | файл лежал в `src/resources`, а не в `src/main/resources` | Maven ищет ресурсы строго в стандартных путях (`src/main/resources`, `src/test/resources`) |
| `TimeoutException` на `waitVisible`/`waitVisibleAll` | элемент есть в DOM, но скрыт стилями (карусель, свёрнутый блок) | переключиться на `waitPresent`/`waitPresentAll`, при необходимости читать `getAttribute("textContent")` вместо `getText()` |
| `NoSuchElementException` по конкретному xpath/css | локатор не совпадает с реальным DOM (придуман/устарел) | всегда проверять через DevTools (`$x(...)` в Console) перед тем как вписывать локатор в код |
| `StaleElementReferenceException` | элемент найден до того, как DOM перерисовался (AJAX после клика/сортировки/фильтра), ссылка на старый узел устарела | не хранить `WebElement`/список заранее — искать заново непосредственно перед действием; при повторении — retry-цикл (`for` с `catch (StaleElementReferenceException e)`) |
| CSS-локатор не находит элемент с несколькими классами | классы через пробел в CSS-селекторе трактуются как вложенность (`.a .b` = "b внутри a"), а не "оба класса на одном элементе" | несколько классов одного элемента через точку без пробела: `.a.b` |
| Тест "падает то так, то так" на медленной загрузке | использовался `Thread.sleep(N)` вместо ожидания условия | заменять на `waitClickable`/`waitVisible` перед конкретным действием — ждать событие, а не фиксированное время |

## Как запустить

```
mvn clean test
```

Перед первым реальным прогоном проверь актуальность `Locator.*` через
DevTools — вёрстка сайта могла измениться с момента последнего обновления
локаторов.