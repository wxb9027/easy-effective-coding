package easy.effective.coding.leetcode;

import org.elasticsearch.common.util.concurrent.CountDown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * 按序打印
 */
class P1114 {

    class Foo {

        CountDownLatch firstLatch = new CountDownLatch(1);
        CountDownLatch secondLatch = new CountDownLatch(1);

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            firstLatch.countDown();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            // printSecond.run() outputs "second". Do not change or remove this line.
            firstLatch.await();
            printSecond.run();
            secondLatch.countDown();
        }

        public void third(Runnable printThird) throws InterruptedException {
            // printThird.run() outputs "third". Do not change or remove this line.
            secondLatch.await();
            printThird.run();
        }
    }

   /* class Foo {

        Semaphore secondSemahpore = new Semaphore(-1);
        Semaphore thirdSemahpore = new Semaphore(0 );

        public Foo() {

        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            secondSemahpore.release();
            secondSemahpore.release();
        }

        public void second(Runnable printSecond) throws InterruptedException {

            // printSecond.run() outputs "second". Do not change or remove this line.
            secondSemahpore.acquire();
            printSecond.run();
            thirdSemahpore.release();
        }

        public void third(Runnable printThird) throws InterruptedException {

            // printThird.run() outputs "third". Do not change or remove this line.
            thirdSemahpore.acquire();
            printThird.run();
        }
    }*/

}
