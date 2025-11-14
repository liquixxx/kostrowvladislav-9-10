public class TernarySearch {

    public static int ternarySearch(int[] arr, int left, int right, int x) {
        if (right >= left) {
            // Находим два разделителя
            int m1 = left + (right - left) / 3;
            int m2 = right - (right - left) / 3;

            // Если элемент найден в одном из разделителей
            if (arr[m1] == x) {
                return m1;
            }
            if (arr[m2] == x) {
                return m2;
            }

            // Если элемент находится в первой части
            if (x < arr[m1]) {
                return ternarySearch(arr, left, m1 - 1, x);
            }
            // Если элемент находится в третьей части
            else if (x > arr[m2]) {
                return ternarySearch(arr, m2 + 1, right, x);
            }
            // Если элемент находится во второй части
            else {
                return ternarySearch(arr, m1 + 1, m2 - 1, x);
            }
        }
        // Элемент не найден
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40};
        int x = 10;
        int left = 0;
        int right = arr.length - 1;

        int result = ternarySearch(arr, left, right, x);

        if (result == -1) {
            System.out.println("Элемент не найден");
        } else {
            System.out.println("Элемент найден на индексе: " + result);
        }
    }
}
public class TernarySearch {

    public static int ternarySearch(int[] arr, int left, int right, int x) {
        if (right >= left) {
            // Находим два разделителя
            int m1 = left + (right - left) / 3;
            int m2 = right - (right - left) / 3;

            // Если элемент найден в одном из разделителей
            if (arr[m1] == x) {
                return m1;
            }
            if (arr[m2] == x) {
                return m2;
            }

            // Если элемент находится в первой части
            if (x < arr[m1]) {
                return ternarySearch(arr, left, m1 - 1, x);
            }
            // Если элемент находится в третьей части
            else if (x > arr[m2]) {
                return ternarySearch(arr, m2 + 1, right, x);
            }
            // Если элемент находится во второй части
            else {
                return ternarySearch(arr, m1 + 1, m2 - 1, x);
            }
        }
        // Элемент не найден
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40};
        int x = 10;
        int left = 0;
        int right = arr.length - 1;

        int result = ternarySearch(arr, left, right, x);

        if (result == -1) {
            System.out.println("Элемент не найден");
        } else {
            System.out.println("Элемент найден на индексе: " + result);
        }
    }
}
