public class BeadSort {
    
    /**
     * Сортировка бусинами (градитационная сортировка)
     * Визуализация: представьте бусины, падающие под действием гравитации
     * Каждое число представляется как набор бусин в столбце
     */
    
    /**
     * Основной метод сортировки бусинами
     * param arr - массив неотрицательных целых чисел для сортировки
     */
    public static void beadSort(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        
        // Находим максимальное значение в массиве
        // Это определяет высоту "рейки" для бусин
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        
        // Создаем матрицу бусин
        // Каждый столбец представляет одно число
        // Каждая ячейка = 1 (бусина) или 0 (отсутствие бусины)
        char[][] beads = new char[arr.length][max];
        
        // Инициализируем матрицу бусин
        // Для каждого числа arr[i] размещаем бусины в нижней части столбца
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < max; j++) {
                // Если j < arr[i], то размещаем бусину (1)
                // Иначе оставляем пустое место (0)
                if (j < arr[i]) {
                    beads[i][j] = 1; // Бусина присутствует
                } else {
                    beads[i][j] = 0; // Бусина отсутствует
                }
            }
        }
        
        // Даем бусинам "упасть" под действием гравитации
        // Бусины падают вниз, заполняя пустоты
        for (int j = 0; j < max; j++) {
            int sum = 0;
            
            // Считаем количество бусин в текущей строке
            for (int i = 0; i < arr.length; i++) {
                sum += beads[i][j];
                beads[i][j] = 0; // Очищаем текущую строку
            }
            
            // Размещаем бусины в нижней части столбцов (гравитация)
            for (int i = arr.length - 1; i >= arr.length - sum; i--) {
                beads[i][j] = 1; // Бусины падают вниз
            }
        }
        
        // Преобразуем матрицу бусин обратно в числа
        // Считаем количество бусин в каждом столбце
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < max; j++) {
                if (beads[i][j] == 1) {
                    count++;
                }
            }
            arr[i] = count;
        }
    }
    
    /**
     * Альтернативная более эффективная реализация
     */
    public static void beadSortOptimized(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        
        // Находим максимальное значение
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        
        // Создаем массив для подсчета бусин на каждом уровне
        int[] beadCounts = new int[max];
        
        // Распределяем бусины по уровням
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i]; j++) {
                beadCounts[j]++;
            }
        }
        
        // Собираем бусины обратно в числа
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < max; j++) {
                if (beadCounts[j] > arr.length - 1 - i) {
                    count++;
                }
            }
            arr[i] = count;
        }
    }
    
    /**
     * Визуализация матрицы бусин (для демонстрации)
     */
    public static void printBeads(char[][] beads) {
        for (int j = beads[0].length - 1; j >= 0; j--) {
            for (int i = 0; i < beads.length; i++) {
                System.out.print(beads[i][j] == 1 ? "○ " : "· ");
            }
            System.out.println();
        }
        System.out.println();
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
     * Демонстрация работы алгоритма
     */
    public static void main(String[] args) {
        System.out.println("=== Сортировка бусинами на Java ===");
        
        int[] arr1 = {3, 1, 4, 1, 5, 2};
        System.out.println("Исходный массив:");
        printArray(arr1);
        
        beadSort(arr1);
        
        System.out.println("Отсортированный массив:");
        printArray(arr1);
        
        // Пример с визуализацией
        System.out.println("\nДемонстрация с визуализацией:");
        int[] arr2 = {2, 4, 1, 3};
        System.out.println("Исходный массив:");
        printArray(arr2);
        
        // Создаем и визуализируем начальное состояние бусин
        int max = 4;
        char[][] beads = new char[arr2.length][max];
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < max; j++) {
                beads[i][j] = (j < arr2[i]) ? (char)1 : (char)0;
            }
        }
        
        System.out.println("Начальное расположение бусин:");
        printBeads(beads);
        
        beadSort(arr2);
        System.out.println("Отсортированный массив:");
        printArray(arr2);
        
        // Тестируем оптимизированную версию
        int[] arr3 = {5, 3, 8, 1, 2};
        System.out.println("\nОптимизированная версия:");
        System.out.println("Исходный массив:");
        printArray(arr3);
        
        beadSortOptimized(arr3);
        
        System.out.println("Отсортированный массив:");
        printArray(arr3);
    }
}
