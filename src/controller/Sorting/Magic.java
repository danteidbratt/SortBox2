package controller.Sorting;

import model.Retreivable;
import view.Sortable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Magic {

    private int initialThreadCount;
    private Sortable sortable;
    private final Retreivable data;

    public Magic(Sortable sortable, Retreivable data) {
        this.sortable = sortable;
        this.data = data;
    }

    public List<Algorithm> loadAlgorithms() {
        List<Algorithm> sortingAlgorithms = new ArrayList<>();

        sortingAlgorithms.add(new Algorithm("Insertion", () -> {
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
            done();
        }));

        sortingAlgorithms.add(new Algorithm("Bubble", () -> {
            start();
            boolean ordered = false;
            int round = 0;
            int current;
            while (!ordered) {
                ordered = true;
                current = scan(0);
                for (int i = 0; i < data.getSize() - 1 - round; i++) {
                    if (current > scan(i + 1)) {
                        swap(i, i + 1);
                        ordered = false;
                    } else {
                        current = scan(i + 1);
                    }
                }
                round++;
            }
            done();
        }));

        sortingAlgorithms.add(new Algorithm("Sliding", () -> {
            start();
            boolean ordered = false;
            while (!ordered) {
                ordered = true;
                for (int i = 0; i < data.getSize() - 1; i += 2) {
                    if (scan(i) > scan(i + 1)) {
                        swap(i, i + 1);
                        ordered = false;
                    }
                }
                for (int i = 1; i < data.getSize() - 1; i += 2) {
                    if (scan(i) > scan(i + 1)) {
                        swap(i, i + 1);
                        ordered = false;
                    }
                }
            }
            done();
        }));

        sortingAlgorithms.add(new Algorithm("Shaker", () -> {
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
                        if (!grabbed) {
                            grabbed = true;
                        }
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
                        if (!grabbed) {
                            grabbed = true;
                        }
                    } else {
                        current = contender;
                    }
                }
                bottomWall++;
            }
            done();
        }));

        sortingAlgorithms.add(new Algorithm("Shell", new Runnable() {
            @Override
            public void run() {
                start();
                Arrays.asList(5, 2, 1).forEach(this::generateIndexList);
                done();
            }

            private void generateIndexList(int interval) {
                int[] temp;
                int tempLength;
                for (int i = 0; i < interval; i++) {
                    tempLength = ((data.getSize() - i) / interval);
                    temp = new int[tempLength];
                    for (int j = 0; j < tempLength; j++) {
                        temp[j] = i + (interval * j);
                    }
                    if (temp.length > 1) {
                        sortByIndexList(temp);
                    }
                }
            }

            private void sortByIndexList(int[] temp) {
                int current;
                for (int i = 1; i < temp.length; i++) {
                    current = scan(temp[i]);
                    for (int j = i; j > 0; j--) {
                        if (scan(temp[j - 1]) > current) {
                            swap(temp[j - 1], temp[j]);
                        } else {
                            break;
                        }
                    }
                }
            }
        }));

        sortingAlgorithms.add(new Algorithm("Quick", new Runnable() {

            @Override
            public void run() {
                start();
                quickBox(0, data.getSize() - 1);
                done();
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

        sortingAlgorithms.add(new Algorithm("MultiQuick", new Runnable() {

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
                if (initialThreadCount == Thread.activeCount()) {
                    done();
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

    private void done() {
        sortable.stopTimer();
        Algorithm.setBusy(false);
    }

}