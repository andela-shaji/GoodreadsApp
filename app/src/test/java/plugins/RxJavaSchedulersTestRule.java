package plugins;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class RxJavaSchedulersTestRule implements TestRule {

    public static RxJavaSchedulersTestRule rule() {
        return new RxJavaSchedulersTestRule();
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                resetPlugins();
                RxJavaPlugins.registerSchedulersHook(new TestRxJavaSchedulersHook());
                RxAndroidPlugins.getInstance().registerSchedulersHook(new TestRxAndroidSchedulersHook());

                base.evaluate();

                resetPlugins();
            }
        };
    }

    private void resetPlugins() {
        RxJavaPlugins.getInstance().reset();
        RxAndroidPlugins.getInstance().reset();
    }

    private class TestRxAndroidSchedulersHook extends RxAndroidSchedulersHook {
        @Override
        public Scheduler getMainThreadScheduler() {
            return Schedulers.immediate();
        }
    }

    private class TestRxJavaSchedulersHook extends RxJavaSchedulersHook {
        @Override
        public Scheduler getIOScheduler() {
            return Schedulers.immediate();
        }
    }
}