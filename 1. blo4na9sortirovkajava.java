import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    
    /**
     * Реализация блочной (корзинной) сортировки
     * @param arr - массив для сортировки
     * @param bucketCount - количество корзин (блоков)
     */
    public static void bucketSort(double[] arr, int bucketCount) {
        // Проверка на пустой массив или массив с одним элементом
        if (arr.length <= 1) {
            return;
        }
        
        // Находим минимальное и максимальное значение в массиве
        // Это нужно для равномерного распределения элементов по корзинам
        double minVal = arr[0];
        double maxVal = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minVal) {
                minVal = arr[i];
            }
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }
        
        // Создаем массив корзин (блоков)
        // Каждая корзина - это список чисел
        List<Double>[] buckets = new ArrayList[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }
        
        // Распределяем элементы по корзинам
        // Диапазон значений: от minVal до maxVal
        // Размер каждого интервала: (maxVal - minVal) / bucketCount
        double range = maxVal - minVal;
        for (int i = 0; i < arr.length; i++) {
            // Вычисляем индекс корзины для текущего элемента
            // Формула: (значение - минимум) / диапазон * количество_корзин
            int bucketIndex = (int)((arr[i] - minVal) / range * (bucketCount - 1));
            buckets[bucketIndex].add(arr[i]);
        }
        
        // Сортируем каждую корзину отдельно
        // Используем встроенную сортировку Collections.sort()
        for (int i = 0; i < bucketCount; i++) {
            Collections.sort(buckets[i]);
        }
        
        // Объединяем отсортированные корзины обратно в исходный массив
        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j);
            }
        }
    }
    
    /**
     * Вспомогательный метод для вывода массива
     */
    public static void printArray(double[] arr) {
        for (double num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
    /**
     * Демонстрация работы алгоритма
     */
    public static void main(String[] args) {
        // Тестовый массив с дробными числами
        double[] numbers = {0.42, 0.32, 0.33, 0.52, 0.37, 0.47, 0.51, 0.29, 0.61, 0.71};
        
        System.out.println("Исходный массив:");
        printArray(numbers);
        
        // Вызываем блочную сортировку с 5 корзинами
        bucketSort(numbers, 5);
        
        System.out.println("Отсортированный массив:");
        printArray(numbers);
        
        // Дополнительный пример с целыми числами
        double[] numbers2 = {5, 3, 8, 1, 9, 2, 7, 4, 6, 0};
        
        System.out.println("\nВторой пример (целые числа):");
        System.out.println("Исходный массив:");
        printArray(numbers2);
        
        bucketSort(numbers2, 4);
        
        System.out.println("Отсортированный массив:");
        printArray(numbers2);
    }
}
