public class TernarySearch {

    // Рекурсивная реализация
    public static int ternarySearchRecursive(int[] arr, int left, int right, int target) {
        if (right >= left) {
            // Находим две опорные точки
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;

            // Если элемент найден в mid1 или mid2
            if (arr[mid1] == target) {
                return mid1;
            }
            if (arr[mid2] == target) {
                return mid2;
            }

            // Рекурсивно вызываем для соответствующей подмассива
            if (target < arr[mid1]) {
                return ternarySearchRecursive(arr, left, mid1 - 1, target);
            } else if (target > arr[mid2]) {
                return ternarySearchRecursive(arr, mid2 + 1, right, target);
            } else {
                return ternarySearchRecursive(arr, mid1 + 1, mid2 - 1, target);
            }
        }

        // Элемент не найден
        return -1;
    }

    // Итеративная реализация
    public static int ternarySearchIterative(int[] arr, int left, int right, int target) {
        while (right >= left) {
            // Находим две опорные точки
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;

            // Если элемент найден в mid1 или mid2
            if (arr[mid1] == target) {
                return mid1;
            }
            if (arr[mid2] == target) {
                return mid2;
            }

            // Сужаем диапазон поиска
            if (target < arr[mid1]) {
                right = mid1 - 1;
            } else if (target > arr[mid2]) {
                left = mid2 + 1;
            } else {
                left = mid1 + 1;
                right = mid2 - 1;
            }
        }

        // Элемент не найден
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 };
        int target = 11;

        // Вызов рекурсивной функции
        int resultRecursive = ternarySearchRecursive(arr, 0, arr.length - 1, target);
        if (resultRecursive != -1) {
            System.out.println("Рекурсивная версия: Элемент найден по индексу " + resultRecursive);
        } else {
            System.out.println("Рекурсивная версия: Элемент не найден");
        }

        // Вызов итеративной функции
        int resultIterative = ternarySearchIterative(arr, 0, arr.length - 1, target);
        if (resultIterative != -1
