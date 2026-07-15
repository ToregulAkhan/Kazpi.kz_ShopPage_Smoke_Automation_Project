package kaspi_shop.listeners;


import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("▶ Тест начался: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("✅ Тест прошел: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("❌ Тест упал: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("⏭ Тест пропущен: " + result.getName());
    }
}
