public class ExponentialSearch {
    public static int exponentialSearch(int[] arr, int target) {
        int n = arr.length;
        
        // Обработка пустого массива
        if (n == 0) {
            return -1;
        }
        
        // Если искомый элемент в начале массива
        if (arr[0] == target) {
            return 0;
        }
        
        // Экспоненциально увеличиваем индекс, пока не найдем диапазон
        int i = 1;
        while (i < n && arr[i] <= target) {
            i *= 2; // Удваиваем размер шага
        }
        
        // Выполняем бинарный поиск в найденном диапазоне
        int left = i / 2; // Начало диапазона
        int right = Math.min(i, n - 1); // Конец диапазона (не выходим за границы)
        
        return binarySearch(arr, target, left, right);
    }
    
    /**
     * Вспомогательный метод бинарного поиска
     */
    private static int binarySearch(int[] arr, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2; // Предотвращаем переполнение
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1; // Элемент не найден
    }
    
    /**
     * Рекурсивная версия экспоненциального поиска
     */
    public static int exponentialSearchRecursive(int[] arr, int target) {
        if (arr.length == 0) {
            return -1;
        }
        
        if (arr[0] == target) {
            return 0;
        }
        
        return exponentialSearchRecursive(arr, target, 1);
    }
    
    private static int exponentialSearchRecursive(int[] arr, int target, int i) {
        // Базовый случай: дошли до конца массива или нашли границу
        if (i >= arr.length || arr[i] > target) {
            int left = i / 2;
            int right = Math.min(i, arr.length - 1);
            return binarySearchRecursive(arr, target, left, right);
        }
        
        // Рекурсивно удваиваем шаг
        return exponentialSearchRecursive(arr, target, i * 2);
    }
    
    /**
     * Рекурсивный бинарный поиск
     */
    private static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }
    
    /**
     * Демонстрация работы алгоритма с визуализацией шагов
     */
    public static void exponentialSearchWithSteps(int[] arr, int target) {
        System.out.println("Экспоненциальный поиск элемента " + target);
        System.out.println("Массив: ");
        printArray(arr);
        
        int n = arr.length;
        
        if (n == 0) {
            System.out.println("Массив пуст");
            return;
        }
        
        System.out.println("Шаг 1: Проверяем первый элемент");
        if (arr[0] == target) {
            System.out.println("Элемент найден на позиции 0");
            return;
        }
        
        System.out.println("Шаг 2: Экспоненциальное расширение диапазона");
        int i = 1;
        int stepCount = 1;
        
        while (i < n && arr[i] <= target) {
            System.out.println("Шаг " + stepCount + ": проверяем индекс " + i + 
                             " (значение: " + arr[i] + ")");
            i *= 2;
            stepCount++;
        }
        
        int left = i / 2;
        int right = Math.min(i, n - 1);
        
        System.out.println("Найден диапазон для бинарного поиска: [" + left + ", " + right + "]");
        System.out.println("Шаг 3: Выполняем бинарный поиск в диапазоне [" + left + ", " + right + "]");
        
        int result = binarySearchWithSteps(arr, target, left, right);
        
        if (result != -1) {
            System.out.println("Элемент найден на позиции: " + result);
        } else {
            System.out.println("Элемент не найден");
        }
        System.out.println();
    }
    
    /**
     * Бинарный поиск с выводом шагов
     */
    private static int binarySearchWithSteps(int[] arr, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println("  Проверяем индекс " + mid + " (значение: " + arr[mid] + ")");
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                System.out.println("  Искомый элемент больше, ищем в правой части");
                left = mid + 1;
            } else {
                System.out.println("  Искомый элемент меньше, ищем в левой части");
                right = mid - 1;
            }
        }
        return -1;
    }
    
    /**
     * Вспомогательный метод для вывода массива
     */
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            if (i < arr.length - 1) {
                System.out.print("");
            }
        }
        System.out.println();
    }
    
    /**
     * Сравнение с бинарным поиском
     */
    public static void compareWithBinarySearch(int[] arr, int target) {
        System.out.println("Сравнение алгоритмов для элемента " + target);
        
        long startTime = System.nanoTime();
        int expResult = exponentialSearch(arr, target);
        long expTime = System.nanoTime();
        
        long startTime2 = System.nanoTime();
        int binResult = binarySearch(arr, target, 0, arr.length - 1);
        long binTime = System.nanoTime();
        
        System.out.println("Экспоненциальный поиск: результат = " + expResult + 
                         ", время = " + (expTime - startTime) + " нс");
        System.out.println("Бинарный поиск: результат = " + binResult + 
                         ", время = " + (binTime - startTime2) + " нс");
        System.out.println();
    }
    
    /**
     * Демонстрация работы алгоритма
     */
    public static void main(String[] args) {
        System.out.println("=== Экспоненциальный поиск на Java ===");
        
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29};
        
        // Демонстрация с визуализацией шагов
        exponentialSearchWithSteps(sortedArray, 13);
        exponentialSearchWithSteps(sortedArray, 1);
        exponentialSearchWithSteps(sortedArray, 29);
        exponentialSearchWithSteps(sortedArray, 100); // Несуществующий элемент
        
        // Тестирование обычной версии
        System.out.println("Тестирование обычной версии:");
        int[] testArray = {2, 5, 8, 12, 16, 23, 38, 45, 67, 89, 91, 95};
        printArray(testArray);
        
        int[] targets = {16, 45, 89, 2, 100};
        for (int target : targets) {
            int result = exponentialSearch(testArray, target);
            if (result != -1) {
                System.out.println("Элемент " + target + " найден на позиции " + result);
            } else {
                System.out.println("Элемент " + target + " не найден");
            }
        }
        
        // Тестирование рекурсивной версии
        System.out.println("\nТестирование рекурсивной версии:");
        for (int target : targets) {
            int result = exponentialSearchRecursive(testArray, target);
            if (result != -1) {
                System.out.println("Элемент " + target + " найден на позиции " + result);
            } else {
                System.out.println("Элемент " + target + " не найден");
            }
        }
        
        // Сравнение производительности
        System.out.println("\nСравнение производительности:");
        int[] largeArray = new int[10000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i * 3; // Кратные 3 числа
        }
        
        compareWithBinarySearch(largeArray, 2997);  // Элемент в начале
        compareWithBinarySearch(largeArray, 14997); // Элемент в середине  
        compareWithBinarySearch(largeArray, 29997); // Элемент в конце
        
        // Особый случай - поиск несуществующего элемента
        System.out.println("Поиск несуществующего элемента:");
        compareWithBinarySearch(largeArray, 29998);
    }
}
