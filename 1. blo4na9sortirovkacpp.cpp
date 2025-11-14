#include <iostream>
#include <vector>
#include <algorithm>
#include <limits>

using namespace std;

/**
 * Реализация блочной (корзинной) сортировки
 * @param arr - массив для сортировки
 * @param bucketCount - количество корзин (блоков)
 */
void bucketSort(vector<double>& arr, int bucketCount) {
    // Проверка на пустой массив или массив с одним элементом
    if (arr.size() <= 1) {
        return;
    }
    
    // Находим минимальное и максимальное значение в массиве
    // Это нужно для равномерного распределения элементов по корзинам
    double minVal = arr[0];
    double maxVal = arr[0];
    for (size_t i = 1; i < arr.size(); i++) {
        if (arr[i] < minVal) {
            minVal = arr[i];
        }
        if (arr[i] > maxVal) {
            maxVal = arr[i];
        }
    }
    
    // Создаем вектор корзин (блоков)
    // Каждая корзина - это вектор чисел
    vector<vector<double>> buckets(bucketCount);
    
    // Распределяем элементы по корзинам
    // Диапазон значений: от minVal до maxVal
    // Размер каждого интервала: (maxVal - minVal) / bucketCount
    double range = maxVal - minVal;
    
    // Если все элементы одинаковые, просто возвращаем исходный массив
    if (range == 0) {
        return;
    }
    
    for (size_t i = 0; i < arr.size(); i++) {
        // Вычисляем индекс корзины для текущего элемента
        // Формула: (значение - минимум) / диапазон * количество_корзин
        int bucketIndex = static_cast<int>((arr[i] - minVal) / range * (bucketCount - 1));
        // Обеспечиваем, чтобы индекс был в допустимых пределах
        bucketIndex = max(0, min(bucketIndex, bucketCount - 1));
        buckets[bucketIndex].push_back(arr[i]);
    }
    
    // Сортируем каждую корзину отдельно
    // Используем встроенную сортировку sort()
    for (int i = 0; i < bucketCount; i++) {
        sort(buckets[i].begin(), buckets[i].end());
    }
    
    // Объединяем отсортированные корзины обратно в исходный вектор
    int index = 0;
    for (int i = 0; i < bucketCount; i++) {
        for (size_t j = 0; j < buckets[i].size(); j++) {
            arr[index++] = buckets[i][j];
        }
    }
}

/**
 * Вспомогательный метод для вывода вектора
 */
void printVector(const vector<double>& arr) {
    for (double num : arr) {
        cout << num << " ";
    }
    cout << endl;
}

/**
 * Версия для целых чисел
 */
void bucketSortInt(vector<int>& arr, int bucketCount) {
    if (arr.size() <= 1) {
        return;
    }
    
    int minVal = arr[0];
    int maxVal = arr[0];
    for (size_t i = 1; i < arr.size(); i++) {
        if (arr[i] < minVal) {
            minVal = arr[i];
        }
        if (arr[i] > maxVal) {
            maxVal = arr[i];
        }
    }
    
    vector<vector<int>> buckets(bucketCount);
    double range = static_cast<double>(maxVal - minVal);
    
    if (range == 0) {
        return;
    }
    
    for (size_t i = 0; i < arr.size(); i++) {
        int bucketIndex = static_cast<int>((arr[i] - minVal) / range * (bucketCount - 1));
        bucketIndex = max(0, min(bucketIndex, bucketCount - 1));
        buckets[bucketIndex].push_back(arr[i]);
    }
    
    for (int i = 0; i < bucketCount; i++) {
        sort(buckets[i].begin(), buckets[i].end());
    }
    
    int index = 0;
    for (int i = 0; i < bucketCount; i++) {
        for (size_t j = 0; j < buckets[i].size(); j++) {
            arr[index++] = buckets[i][j];
        }
    }
}

/**
 * Демонстрация работы алгоритма
 */
int main() {
    cout << "=== Блочная сортировка на C++ ===" << endl;
    
    // Тестовый массив с дробными числами
    vector<double> numbers = {0.42, 0.32, 0.33, 0.52, 0.37, 0.47, 0.51, 0.29, 0.61, 0.71};
    
    cout << "Исходный массив (дробные числа):" << endl;
    printVector(numbers);
    
    // Вызываем блочную сортировку с 5 корзинами
    bucketSort(numbers, 5);
    
    cout << "Отсортированный массив:" << endl;
    printVector(numbers);
    
    // Пример с целыми числами
    vector<int> numbers2 = {5, 3, 8, 1, 9, 2, 7, 4, 6, 0};
    
    cout << "\nВторой пример (целые числа):" << endl;
    cout << "Исходный массив: ";
    for (int num : numbers2) {
        cout << num << " ";
    }
    cout << endl;
    
    bucketSortInt(numbers2, 4);
    
    cout << "Отсортированный массив: ";
    for (int num : numbers2) {
        cout << num << " ";
    }
    cout << endl;
    
    // Пример с отрицательными числами
    vector<double> numbers3 = {-0.5, 0.2, -0.8, 0.9, -0.1, 0.4, -0.3, 0.7};
    
    cout << "\nТретий пример (отрицательные числа):" << endl;
    cout << "Исходный массив: ";
    printVector(numbers3);
    
    bucketSort(numbers3, 4);
    
    cout << "Отсортированный массив: ";
    printVector(numbers3);
    
    return 0;
}
