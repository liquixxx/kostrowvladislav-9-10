public class JumpSearch {
    
    /**
     * Поиск скачками - алгоритм поиска в отсортированном массиве
     * Компромисс между линейным и бинарным поиском
     * Идея: делаем прыжки фиксированного размера, затем линейный поиск в блоке
     */
    
    /**
     * Основной метод поиска скачками
     * param arr - отсортированный массив
     * param target - искомый элемент
     * return индекс элемента или -1 если не найден
     */
    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        
        // Обработка пустого массива
        if (n == 0) {
            return -1;
        }
        
        // Определяем оптимальный размер прыжка
        // Обычно используют √n для минимальной сложности
        int step = (int) Math.sqrt(n);
        
        // Находим блок, где может находиться искомый элемент
        int prev = 0;
        int current = step;
        
        // Прыгаем вперед, пока не найдем блок с возможным target
        // Или пока не выйдем за границы массива
        while (current < n && arr[current] <= target) {
            prev = current;
            current += step;
            
            // Если вышли за границы, устанавливаем current как последний индекс
            if (current > n) {
                current = n;
                break;
            }
        }
        
        // Выполняем линейный поиск в найденном блоке
        for (int i = prev; i < current && i < n; i++) {
            if (arr[i] == target) {
                return i; // Элемент найден
            }
            
            // Если текущий элемент больше target, значит target нет в массиве
            // (т.к. массив отсортирован)
            if (arr[i] > target) {
                break;
            }
        }
        
        return -1; // Элемент не найден
    }
    
    /**
     * Рекурсивная версия поиска скачками
     */
    public static int jumpSearchRecursive(int[] arr, int target, int step, int prev) {
        int n = arr.length;
        
        // Базовый случай - вышли за границы
        if (prev >= n) {
            return -1;
        }
        
        // Определяем текущую границу
        int current = Math.min(prev + step, n - 1);
        
        // Если нашли элемент на границе блока
        if (arr[current] == target) {
            return current;
        }
        
        // Если target больше текущего элемента, прыгаем дальше
        if (arr[current] < target) {
            return jumpSearchRecursive(arr, target, step, current + 1);
        }
        
        // Иначе выполняем линейный поиск в текущем блоке
        for (int i = prev; i <= current; i++) {
            if (arr[i] == target) {
                return i;
            }
            if (arr[i] > target) {
                break;
            }
        }
        
        return -1;
    }
    
    /**
     * Вспомогательный метод для вывода массива
     */
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    /**
     * Демонстрация работы алгоритма с визуализацией шагов
     */
    public static void jumpSearchWithSteps(int[] arr, int target) {
        System.out.println("Поиск элемента " + target);
        System.out.println("Массив: ");
        printArray(arr);
        
        int n = arr.length;
        int step = (int) Math.sqrt(n);
        
        System.out.println("Размер прыжка: " + step);
        System.out.println("Шаги поиска:");
        
        int prev = 0;
        int current = step;
        int stepCount = 1;
        
        while (current < n && arr[current] <= target) {
            System.out.println("Прыжок " + stepCount + ": проверяем индекс " + current + 
                             " (значение: " + arr[current] + ")");
            prev = current;
            current += step;
            stepCount++;
            
            if (current > n) {
                current = n;
                System.out.println("Достигнут конец массива");
            }
        }
        
        System.out.println("Линейный поиск в блоке [" + prev + ", " + 
                         Math.min(current, n-1) + "]");
        
        int result = -1;
        for (int i = prev; i < current && i < n; i++) {
            System.out.println("Проверяем индекс " + i + " (значение: " + arr[i] + ")");
            if (arr[i] == target) {
                result = i;
                break;
            }
            if (arr[i] > target) {
                break;
            }
        }
        
        if (result != -1) {
            System.out.println("Элемент найден на позиции: " + result);
        } else {
            System.out.println("Элемент не найден");
        }
        System.out.println();
    }
    
    /**
     * Демонстрация работы алгоритма
     */
    public static void main(String[] args) {
        System.out.println("=== Поиск скачками на Java ===");
        
        int[] sortedArray = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610};
        
        // Демонстрация с визуализацией шагов
        jumpSearchWithSteps(sortedArray, 55);
        jumpSearchWithSteps(sortedArray, 34);
        jumpSearchWithSteps(sortedArray, 100); // Несуществующий элемент
        
        // Тестирование обычной версии
        System.out.println("Тестирование обычной версии:");
        int[] testArray = {2, 5, 8, 12, 16, 23, 38, 45, 67, 89};
        printArray(testArray);
        
        int[] targets = {16, 45, 100, 2};
        for (int target : targets) {
            int result = jumpSearch(testArray, target);
            if (result != -1) {
                System.out.println("Элемент " + target + " найден на позиции " + result);
            } else {
                System.out.println("Элемент " + target + " не найден");
            }
        }
        
        // Тестирование рекурсивной версии
        System.out.println("\nТестирование рекурсивной версии:");
        int step = (int) Math.sqrt(testArray.length);
        for (int target : targets) {
            int result = jumpSearchRecursive(testArray, target, step, 0);
            if (result != -1) {
                System.out.println("Элемент " + target + " найден на позиции " + result);
            } else {
                System.out.println("Элемент " + target + " не найден");
            }
        }
        
        // Сравнение производительности на большом массиве
        System.out.println("\nСравнение на большом массиве:");
        int[] largeArray = new int[1000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i * 2; // Четные числа
        }
        
        int searchValue = 498;
        long startTime = System.nanoTime();
        int index = jumpSearch(largeArray, searchValue);
        long endTime = System.nanoTime();
        
        System.out.println("Поиск " + searchValue + " в массиве из 1000 элементов");
        System.out.println("Результат: " + (index != -1 ? "найден на позиции " + index : "не найден"));
        System.out.println("Время выполнения: " + (endTime - startTime) + " наносекунд");
    }
}
