#include <iostream>
#include <vector>
#include <algorithm>
#include <chrono>

using namespace std;
int binarySearch(const vector<int>& arr, int target, int left, int right) {
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
    return -1;
}

/**
 * Основной метод экспоненциального поиска
 */
int exponentialSearch(const vector<int>& arr, int target) {
    int n = arr.size();
    
    if (n == 0) {
        return -1;
    }
    
    // Проверяем первый элемент
    if (arr[0] == target) {
        return 0;
    }
    
    // Экспоненциально увеличиваем индекс
    int i = 1;
    while (i < n && arr[i] <= target) {
        i *= 2;
    }
    
    // Бинарный поиск в найденном диапазоне
    int left = i / 2;
    int right = min(i, n - 1);
    
    return binarySearch(arr, target, left, right);
}

/**
 * Рекурсивная версия экспоненциального поиска
 */
int exponentialSearchRecursive(const vector<int>& arr, int target, int i = 1) {
    int n = arr.size();
    
    if (n == 0) return -1;
    if (arr[0] == target) return 0;
    
    // Базовый случай
    if (i >= n || arr[i] > target) {
        int left = i / 2;
        int right = min(i, n - 1);
        
        // Рекурсивный бинарный поиск
        function<int(int, int)> binSearch = [&](int l, int r) -> int {
            if (l > r) return -1;
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) return binSearch(mid + 1, r);
            else return binSearch(l, mid - 1);
        };
        
        return binSearch(left, right);
    }
    
    // Рекурсивный вызов с удвоенным шагом
    return exponentialSearchRecursive(arr, target, i * 2);
}

/**
 * Демонстрация работы алгоритма с визуализацией шагов
 */
void exponentialSearchWithSteps(const vector<int>& arr, int target) {
    cout << "Экспоненциальный поиск элемента " << target << endl;
    cout << "Массив: ";
    for (size_t i = 0; i < arr.size(); i++) {
        cout << arr[i];
        if (i < arr.size() - 1) cout << " ";
    }
    cout << endl;
    
    int n = arr.size();
    
    if (n == 0) {
        cout << "Массив пуст" << endl;
        return;
    }
    
    cout << "Шаг 1: Проверяем первый элемент" << endl;
    if (arr[0] == target) {
        cout << "Элемент найден на позиции 0" << endl;
        return;
    }
    
    cout << "Шаг 2: Экспоненциальное расширение диапазона" << endl;
    int i = 1;
    int stepCount = 1;
    
    while (i < n && arr[i] <= target) {
        cout << "Шаг " << stepCount << ": проверяем индекс " << i 
             << " (значение: " << arr[i] << ")" << endl;
        i *= 2;
        stepCount++;
    }
    
    int left = i / 2;
    int right = min(i, n - 1);
    
    cout << "Найден диапазон для бинарного поиска: [" << left << ", " << right << "]" << endl;
    cout << "Шаг 3: Выполняем бинарный поиск в диапазоне [" << left << ", " << right << "]" << endl;
    
    // Бинарный поиск с выводом шагов
    int result = -1;
    int l = left, r = right;
    while (l <= r) {
        int mid = l + (r - l) / 2;
        cout << "  Проверяем индекс " << mid << " (значение: " << arr[mid] << ")" << endl;
        
        if (arr[mid] == target) {
            result = mid;
            break;
        } else if (arr[mid] < target) {
            cout << "  Искомый элемент больше, ищем в правой части" << endl;
            l = mid + 1;
        } else {
            cout << "  Искомый элемент меньше, ищем в левой части" << endl;
            r = mid - 1;
        }
    }
    
    if (result != -1) {
        cout << "Элемент найден на позиции: " << result << endl;
    } else {
        cout << "Элемент не найден" << endl;
    }
    cout << endl;
}

/**
 * Сравнение с бинарным поиском
 */
void compareWithBinarySearch(const vector<int>& arr, int target) {
    cout << "Сравнение алгоритмов для элемента " << target << endl;
    
    auto start = chrono::high_resolution_clock::now();
    int expResult = exponentialSearch(arr, target);
    auto end = chrono::high_resolution_clock::now();
    auto expTime = chrono::duration_cast<chrono::nanoseconds>(end - start);
    
    start = chrono::high_resolution_clock::now();
    int binResult = binarySearch(arr, target, 0, arr.size() - 1);
    end = chrono::high_resolution_clock::now();
    auto binTime = chrono::duration_cast<chrono::nanoseconds>(end - start);
    
    cout << "Экспоненциальный поиск: результат = " << expResult 
         << ", время = " << expTime.count() << " нс" << endl;
    cout << "Бинарный поиск: результат = " << binResult 
         << ", время = " << binTime.count() << " нс" << endl;
    cout << endl;
}

/**
 * Демонстрация работы алгоритма
 */
int main() {
    cout << "=== Экспоненциальный поиск на C++ ===" << endl;
    
    vector<int> sortedArray = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29};
    
    // Демонстрация с визуализацией шагов
    exponentialSearchWithSteps(sortedArray, 13);
    exponentialSearchWithSteps(sortedArray, 1);
    exponentialSearchWithSteps(sortedArray, 29);
    exponentialSearchWithSteps(sortedArray, 100);
    
    // Тестирование обычной версии
    cout << "Тестирование обычной версии:" << endl;
    vector<int> testArray = {2, 5, 8, 12, 16, 23, 38, 45, 67, 89, 91, 95};
    
    vector<int> targets = {16, 45, 89, 2, 100};
    for (int target : targets) {
        int result = exponentialSearch(testArray, target);
        if (result != -1) {
            cout << "Элемент " << target << " найден на позиции " << result << endl;
        } else {
            cout << "Элемент " << target << " не найден" << endl;
        }
    }
    
    // Тестирование рекурсивной версии
    cout << "\nТестирование рекурсивной версии:" << endl;
    for (int target : targets) {
        int result = exponentialSearchRecursive(testArray, target);
        if (result != -1) {
            cout << "Элемент " << target << " найден на позиции " << result << endl;
        } else {
            cout << "Элемент " << target << " не найден" << endl;
        }
    }
    
    // Сравнение производительности
    cout << "\nСравнение производительности:" << endl;
    vector<int> largeArray(10000);
    for (size_t i = 0; i < largeArray.size(); i++) {
        largeArray[i] = i * 3;
    }
    
    compareWithBinarySearch(largeArray, 2997);  // В начале
    compareWithBinarySearch(largeArray, 14997); // В середине
    compareWithBinarySearch(largeArray, 29997); // В конце
    
    return 0;
}
