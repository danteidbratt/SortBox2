package controller.Sorting;

import model.Retreivable;
import view.Sortable;

import java.util.ArrayList;
import java.util.List;

public class Setup {

    private Sortable sortable;
    private final Retreivable data;
    private final List<Algorithm> sortingAlgorithms;

    public Setup(Sortable sortable, Retreivable data) {
        this.sortable = sortable;
        this.data = data;
        sortingAlgorithms = new ArrayList<>();
        sortingAlgorithms.add(new Algorithm("Insertion", () -> {
            for (int i = 0; i < data.getSize() - 1; i++) {
                for (int j = i; j < data.getSize(); j++) {
                    if (scan(j) == i) {
                        swap(i, j);
                        break;
                    }
                }
            }
        }));

        sortingAlgorithms.add(new Algorithm("Bubble", () -> {
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
        }));

        sortingAlgorithms.add(new Algorithm("Sliding", () -> {
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
        }));

        sortingAlgorithms.add(new Algorithm("Shaker", () -> {
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
        }));

        sortingAlgorithms.add(new Algorithm("Quick", new Runnable() {

            @Override
            public void run() {
                quickBox(0, data.getSize() - 1, 0);
            }

            private void quickBox(int first, int last, int layer) {
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
                if (layer == 0 && pivotIndex == last) {
                    return;
                }
                layer++;
                quickBox(first, pivotIndex - 1, layer);
                quickBox(pivotIndex + 1, last, layer);
            }
        }));

        sortingAlgorithms.add(new Algorithm("MultiQuick", new Runnable() {

            @Override
            public void run() {
                quickBox(0, data.getSize() - 1, 0);
            }

            private int quickBox(int first, int last, int layer) {
                if (last - first < 1) {
                    return 0;
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
                if (layer == 0 && pivotIndex == last) {
                    return 0;
                }
                layer++;
                int finalPivot = pivotIndex;
                int finalLayer = layer;
                new Thread(() -> quickBox(finalPivot + 1, last, finalLayer)).start();
                new Thread(() -> quickBox(first, finalPivot - 1, finalLayer)).start();
                return 0;
            }
        }));
    }

    private synchronized void swap(int first, int second) {
        data.swapValues(first, second);
        sortable.swapPair(data.getValues(), first, second);
    }

    private int scan(int index) {
        sortable.scan(index);
        return data.getValues()[index];
    }

    public List<Algorithm> getSortingAlgorithms() {
        return sortingAlgorithms;
    }

}