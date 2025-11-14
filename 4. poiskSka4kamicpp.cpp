#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

/**
 * Поиск скачками на C++
 * Эффективный алгоритм для отсортированных массивов
 */

/**
 * Основной метод поиска скачками
 * param arr - отсортированный вектор
 * param target - искомый элемент
 * return индекс элемента или -1 если не найден
 */
int jumpSearch(const vector<int>& arr, int target) {
    int n = arr.size();
    
    // Обработка пустого массива
    if (n == 0) {
        return -1;
    }
    
    // Определяем оптимальный размер прыжка
    int step = static_cast<int>(sqrt(n));
    
    // Находим блок, где может находиться искомый элемент
    int prev = 0;
    int current = step;
    
    // Прыгаем вперед, пока не найдем блок с возможным target
    while (current < n && arr[current] <= target) {
        prev = current;
        current += step;
        
        // Если вышли за границы, устанавливаем current как размер массива
        if (current > n) {
            current = n;
            break;
        }
    }
    
    // Линейный поиск в найденном блоке
    for (int i = prev; i < current && i < n; i++) {
        if (arr[i] == target) {
            return i;
        }
        
        // Если текущий элемент больше target, значит target нет в массиве
        if (arr[i] > target) {
            break;
        }
    }
    
    return -1;
}

/**
 * Рекурсивная версия поиска скачками
 */
int jumpSearchRecursive(const vector<int>& arr, int target, int step, int prev) {
    int n = arr.size();
    
    // Базовый случай - вышли за границы
    if (prev >= n) {
        return -1;
    }
    
    // Определяем текущую границу
    int current = min(prev + step, n - 1);
    
    // Если нашли элемент на границе блока
    if (arr[current] == target) {
        return current;
    }
    
    // Если target больше текущего элемента, прыгаем дальше
    if (arr[current] < target) {
        return jumpSearchRecursive(arr, target, step, current + 1);
    }
    
    // Линейный поиск в текущем блоке
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
 * Демонстрация работы алгоритма с визуализацией шагов
 */
void jumpSearchWithSteps(const vector<int>& arr, int target) {
    cout << "Поиск элемента " << target << endl;
    cout << "Массив: ";
    for (int num : arr) {
        cout << num << " ";
    }
    cout << endl;
    
    int n = arr.size();
    int step = static_cast<int>(sqrt(n));
    
    cout << "Размер прыжка: " << step << endl;
    cout << "Шаги поиска:" << endl;
    
    int prev = 0;
    int current = step;
    int stepCount = 1;
    
    while (current < n && arr[current] <= target) {
        cout << "Прыжок " << stepCount << ": проверяем индекс " << current 
             << " (значение: " << arr[current] << ")" << endl;
        prev = current;
        current += step;
        stepCount++;
        
        if (current > n) {
            current = n;
            cout << "Достигнут конец массива" << endl;
        }
    }
    
    cout << "Линейный поиск в блоке [" << prev << ", " 
         << min(current, n-1) << "]" << endl;
    
    int result = -1;
    for (int i = prev; i < current && i < n; i++) {
        cout << "Проверяем индекс " << i << " (значение: " << arr[i] << ")" << endl;
        if (arr[i] == target) {
            result = i;
            break;
        }
        if (arr[i] > target) {
            break;
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
 * Вспомогательная функция для вывода вектора
 */
void printVector(const vector<int>& arr) {
    for (int num : arr) {
        cout << num << " ";
    }
    cout << endl;
}

/**
 * Демонстрация работы алгоритма
 */
int main() {
    cout << "=== Поиск скачками на C++ ===" << endl;
    
    vector<int> sortedArray = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610};
    
    // Демонстрация с визуализацией шагов
    jumpSearchWithSteps(sortedArray, 55);
    jumpSearchWithSteps(sortedArray, 34);
    jumpSearchWithSteps(sortedArray, 100);
    
    // Тестирование обычной версии
    cout << "Тестирование обычной версии:" << endl;
    vector<int> testArray = {2, 5, 8, 12, 16, 23, 38, 45, 67, 89};
    printVector(testArray);
    
    vector<int> targets = {16, 45, 100, 2};
    for (int target : targets) {
        int result = jumpSearch(testArray, target);
        if (result != -1) {
            cout << "Элемент " << target << " найден на позиции " << result << endl;
        } else {
            cout << "Элемент " << target << " не найден" << endl;
        }
    }
    
    // Тестирование рекурсивной версии
    cout << "\nТестирование рекурсивной версии:" << endl;
    int step = static_cast<int>(sqrt(testArray.size()));
    for (int target : targets) {
        int result = jumpSearchRecursive(testArray, target, step, 0);
        if (result != -1) {
            cout << "Элемент " << target << " найден на позиции " << result << endl;
        } else {
            cout << "Элемент " << target << " не найден" << endl;
        }
    }
    
    // Сравнение с std::binary_search
    cout << "\nСравнение с бинарным поиском:" << endl;
    vector<int> largeArray(1000);
    for (int i = 0; i < largeArray.size(); i++) {
        largeArray[i] = i * 2;
    }
    
    int searchValue = 498;
    
    // Поиск скачками
    auto start = chrono::high_resolution_clock::now();
    int jumpResult = jumpSearch(largeArray, searchValue);
    auto end = chrono::high_resolution_clock::now();
    auto jumpDuration = chrono::duration_cast<chrono::nanoseconds>(end - start);
    
    // Бинарный поиск
    start = chrono::high_resolution_clock::now();
    bool binaryResult = binary_search(largeArray.begin(), largeArray.end(), searchValue);
    end = chrono::high_resolution_clock::now();
    auto binaryDuration = chrono::duration_cast<chrono::nanoseconds>(end - start);
    
    cout << "Поиск " << searchValue << " в массиве из 1000 элементов:" << endl;
    cout << "Поиск скачками: " << (jumpResult != -1 ? "найден" : "не найден") 
         << " за " << jumpDuration.count() << " наносекунд" << endl;
    cout << "Бинарный поиск: " << (binaryResult ? "найден" : "не найден") 
         << " за " << binaryDuration.count() << " наносекунд" << endl;
    
    return 0;
}
