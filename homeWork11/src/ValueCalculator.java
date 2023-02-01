import javax.swing.plaf.TableHeaderUI;
import java.util.Arrays;

public class ValueCalculator {
    int length = 1000000;
    float [] numbers = new float[length];
    int halfSize = numbers.length / 2;

    public void doCalc() throws InterruptedException {
        long start = System.currentTimeMillis();

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = 1;
        }

        float[] halfOne = new float[halfSize];
        float[] halfTwo = new float[halfSize];

        System.arraycopy(numbers, 0, halfOne, 0, halfOne.length);
        System.arraycopy(numbers, halfSize, halfTwo, 0, halfTwo.length);

        Thread first = new Thread(() -> arrayValue(halfOne));
        Thread second = new Thread(() -> arrayValue(halfTwo));

        first.start();
        second.start();

        first.join();
        second.join();

        System.arraycopy(halfOne, 0, numbers, 0, halfSize);
        System.arraycopy(halfTwo, 0, numbers, halfSize, halfSize);

        System.out.println(System.currentTimeMillis() - start);
    }

    public static void arrayValue(float [] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
