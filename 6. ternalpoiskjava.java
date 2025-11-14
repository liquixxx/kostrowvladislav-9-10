public class TernarySearch {

    /**
     * Итеративная версия тернарного поиска
     * @param arr отсортированный массив
     * @param target искомый элемент
     * @return индекс элемента или -1, если не найден
     */
    public static int ternarySearchIterative(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        // Продолжаем поиск, пока диапазон валиден
        while (left <= right) {
            // Вычисляем две точки деления диапазона на три части
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;
            
            System.out.println("Поиск в диапазоне [" + left + ", " + right + 
                             "], mid1=" + mid1 + ", mid2=" + mid2);
            
            // Проверяем, не нашли ли элемент в точках деления
            if (arr[mid1] == target) {
                return mid1;  // Элемент найден в первой точке
            }
            if (arr[mid2] == target) {
                return mid2;  // Элемент найден во второй точке
            }
            
            // Определяем, в какой трети продолжать поиск
            if (target < arr[mid1]) {
                // Искомый элемент в левой трети
                right = mid1 - 1;
            } else if (target > arr[mid2]) {
                // Искомый элемент в правой трети
                left = mid2 + 1;
            } else {
                // Искомый элемент в средней трети
                left = mid1 + 1;
                right = mid2 - 1;
            }
        }
        
        // Элемент не найден
        return -1;
    }
    
    /**
     * Рекурсивная версия тернарного поиска
     * @param arr отсортированный массив
     * @param target искомый элемент
     * @param left левая граница поиска
     * @param right правая граница поиска
     * @return индекс элемента или -1, если не найден
     */
    public static int ternarySearchRecursive(int[] arr, int target, int left, int right) {
        // Базовый случай: диапазон невалиден
        if (left > right) {
            return -1;
        }
        
        // Вычисляем точки деления
        int mid1 = left + (right - left) / 3;
        int mid2 = right - (right - left) / 3;
        
        System.out.println("Рекурсивный поиск в [" + left + ", " + right + 
                         "], mid1=" + mid1 + ", mid2=" + mid2);
        
        // Проверяем точки деления
        if (arr[mid1] == target) {
            return mid1;
        }
        if (arr[mid2] == target) {
            return mid2;
        }
        
        // Рекурсивно ищем в соответствующей трети
        if (target < arr[mid1]) {
            // Ищем в левой трети
            return ternarySearchRecursive(arr, target, left, mid1 - 1);
        } else if (target > arr[mid2]) {
            // Ищем в правой трети
            return ternarySearchRecursive(arr, target, mid2 + 1, right);
        } else {
            // Ищем в средней трети
            return ternarySearchRecursive(arr, target, mid1 + 1, mid2 - 1);
        }
    }
    
    /**
     * Обертка для рекурсивной версии с более простым вызовом
     */
    public static int ternarySearchRecursive(int[] arr, int target) {
        return ternarySearchRecursive(arr, target, 0, arr.length - 1);
    }
    
    // Демонстрация работы алгоритма
    public static void main(String[] args) {
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25};
        int target = 13;
        
        System.out.println("Массив для поиска: " + java.util.Arrays.toString(sortedArray));
        System.out.println("Ищем элемент: " + target);
        System.out.println();
        
        // Итеративный поиск
        System.out.println("=== Итеративный тернарный поиск ===");
        int resultIterative = ternarySearchIterative(sortedArray, target);
        System.out.println("Результат итеративного поиска: " + resultIterative);
        System.out.println();
        
        // Рекурсивный поиск
        System.out.println("=== Рекурсивный тернарный поиск ===");
        int resultRecursive = ternarySearchRecursive(sortedArray, target);
        System.out.println("Результат рекурсивного поиска: " + resultRecursive);
        System.out.println();
        
        // Поиск несуществующего элемента
        System.out.println("=== Поиск несуществующего элемента ===");
        int notFound = ternarySearchIterative(sortedArray, 100);
        System.out.println("Результат поиска несуществующего элемента: " + notFound);
        
        // Сравнение с бинарным поиском
        System.out.println();
        System.out.println("=== Сравнение с Arrays.binarySearch ===");
        int binaryResult = java.util.Arrays.binarySearch(sortedArray, target);
        System.out.println("Результат встроенного бинарного поиска: " + binaryResult);
    }
}
