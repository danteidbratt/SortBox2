package controller.Sorting;

import model.Retreivable;
import view.Sortable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Controller {

    private int initialThreadCount;
    private Sortable sortable;
    private final Retreivable data;

    public Controller(Sortable sortable, Retreivable data) {
        this.sortable = sortable;
        this.data = data;
    }

    public List<Algorithm> loadAlgorithms() {
        List<Algorithm> sortingAlgorithms = new ArrayList<>();

        sortingAlgorithms.add(new Algorithm("Slide", () -> {
            start();
            boolean messy = true;
            boolean even = true;
            while (messy || data.isShuffled()) {
                messy = false;
                for (int i = even ? 0 : 1; i < data.getSize() - 1; i += 2) {
                    if (scan(i) > scan(i + 1)) {
                        swap(i, i + 1);
                        messy = true;
                    }
                }
                even = !even;
            }
            finish();
        }));

        sortingAlgorithms.add(new Algorithm("Bubble", () -> {
            start();
            boolean messy = true;
            int round = 0;
            int current;
            int contender;
            while (messy) {
                messy = false;
                current = scan(0);
                for (int i = 0; i < data.getSize() - 1 - round; i++) {
                    contender = scan(i + 1);
                    if (current > contender) {
                        swap(i, i + 1);
                        messy = true;
                    } else {
                        current = contender;
                    }
                }
                round++;
            }
            finish();
        }));

        sortingAlgorithms.add(new Algorithm("Shake", () -> {
            start();
            int bottomWall = 0;
            int topWall = data.getSize() - 1;
            boolean grabbed = true;
            int current;
            int contender = scan(bottomWall);
            while (grabbed) {
                current = contender;
                grabbed = false;
                for (int i = bottomWall; i < topWall; i++) {
                    contender = scan(i + 1);
                    if (!grabbed && current == i) {
                        bottomWall = i;
                    }
                    if (current > contender) {
                        swap(i, i + 1);
                        grabbed = true;
                    } else {
                        current = contender;
                    }
                }
                if (!grabbed) {
                    break;
                }
                topWall--;
                grabbed = false;
                current = contender;
                for (int i = topWall; i > bottomWall; i--) {
                    contender = scan(i - 1);
                    if (!grabbed && current == i) {
                        topWall = i;
                    }
                    if (current < contender) {
                        swap(i, i - 1);
                        grabbed = true;
                    } else {
                        current = contender;
                    }
                }
                bottomWall++;
            }
            finish();
        }));

        sortingAlgorithms.add(new Algorithm("Insert", () -> {
            start();
            int current;
            for (int i = 1; i < data.getSize(); i++) {
                current = scan(i);
                for (int j = i; j > 0; j--) {
                    if (scan(j - 1) > current) {
                        swap(j - 1, j);
                    } else {
                        break;
                    }
                }
            }
            finish();
        }));

        sortingAlgorithms.add(new Algorithm("Wave", new Runnable() {

            @Override
            public void run() {
                start();
                Arrays.asList(5, 2, 1).forEach(interval -> {
                    int[] indexes;
                    int tempLength;
                    for (int i = 0; i < interval; i++) {
                        tempLength = ((data.getSize() - i) / interval);
                        indexes = new int[tempLength];
                        for (int j = 0; j < tempLength; j++) {
                            indexes[j] = i + (interval * j);
                        }
                        if (indexes.length > 1) {
                            sortBy(indexes);
                        }
                    }
                });
                finish();
            }

            private void sortBy(int[] indexes) {
                int current;
                for (int i = 1; i < indexes.length; i++) {
                    current = scan(indexes[i]);
                    for (int j = i; j > 0; j--) {
                        if (scan(indexes[j - 1]) > current) {
                            swap(indexes[j - 1], indexes[j]);
                        } else {
                            break;
                        }
                    }
                }
            }
        }));

        sortingAlgorithms.add(new Algorithm("Shell", () -> {
            start();
            Arrays.asList(301, 132, 57, 23, 10, 4, 1).forEach(interval -> {
                int current, contender, currentIndex;
                for (int i = interval; i < data.getSize(); i++) {
                    currentIndex = i;
                    current = scan(i);
                    for (int j = i - interval; j >= 0; j -= interval) {
                        contender = scan(j);
                        if (current < contender) {
                            swap(currentIndex, j);
                            currentIndex = j;
                        } else {
                            break;
                        }
                    }
                }
            });
            finish();
        }));

        sortingAlgorithms.add(new Algorithm("Quick", new Runnable() {

            @Override
            public void run() {
                start();
                quickBox(0, data.getSize() - 1);
                finish();
            }

            private void quickBox(int first, int last) {
                if (last - first < 1) {
                    return;
                }
                int pivotIndex = last;
                int pivotValue = scan(pivotIndex);
                for (int i = first; i < pivotIndex; ) {
                    if (scan(i) > pivotValue) {
                        swap(pivotIndex, i);
                        swap(i, pivotIndex - 1);
                        pivotIndex--;
                    } else {
                        i++;
                    }
                }
                quickBox(first, pivotIndex - 1);
                quickBox(pivotIndex + 1, last);
            }
        }));

        sortingAlgorithms.add(new Algorithm("Beast", new Runnable() {

            @Override
            public void run() {
                start();
                initialThreadCount = Thread.activeCount();
                quickBox(0, data.getSize() - 1);
            }

            private void quickBox(int first, int last) {
                if (last - first < 1) {
                    terminate();
                    return;
                }
                int pivotIndex = last;
                int pivotValue = scan(pivotIndex);
                for (int i = first; i < pivotIndex; ) {
                    if (scan(i) > pivotValue) {
                        swap(pivotIndex, i);
                        swap(i, pivotIndex - 1);
                        pivotIndex--;
                    } else {
                        i++;
                    }
                }
                int finalPivot = pivotIndex;
                new Thread(() -> quickBox(finalPivot + 1, last)).start();
                new Thread(() -> quickBox(first, finalPivot - 1)).start();
                terminate();
            }

            private synchronized void terminate() {
                if (initialThreadCount == Thread.activeCount() + 1) {
                    finish();
                }
            }

        }));

        return sortingAlgorithms;
    }


    private synchronized void swap(int first, int second) {
        data.swapValues(first, second);
        sortable.swapPair(data.getValues(), first, second);
    }

    private int scan(int index) {
        sortable.scan(index);
        return data.getValues()[index];
    }

    private void start() {
        sortable.startTimer();
    }

    private void finish() {
        sortable.stopTimer();
        Algorithm.setBusy(false);
    }

}