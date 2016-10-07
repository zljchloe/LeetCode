package Matrix;

import java.util.*;

/**
 * Created by lyujiazhang on 10/6/16.
 * https://leetcode.com/problems/shortest-distance-from-all-buildings/
 *
 * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
 * Each 0 marks an empty land which you can pass by freely.
 * Each 1 marks a building which you cannot pass through.
 * Each 2 marks an obstacle which you cannot pass through.
 * For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 *
 * 1. BFS solution.
 * 2. For each element whose value is 0, calculate distance sum to all 1s from it using BFS.
 * 3. Clean up the Entry matrix after each loop to start over.
 * 4. Pre-process to count the number of 1s, and only update the min dist sum when all 1s have been visited.
 * 5. Do not go to 2s, and update sum when reaching each 1.
 */
public class ShortestDistanceFromAllBuildings {
    static class Entry {
        int x;
        int y;
        int val;
        int sum;
        boolean isVisited;
        Entry(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
            sum = 0;
            isVisited = false;
        }
        void startOver() {
            this.isVisited = false;
            this.sum = 0;
        }
    }
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        Entry[][] entryGrid = new Entry[grid.length][grid[0].length];
        int buildingNum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                entryGrid[i][j] = new Entry(i, j, grid[i][j]);
                if (entryGrid[i][j].val == 1) {
                    buildingNum++;
                }
            }
        }
        int[] min = new int[] {Integer.MAX_VALUE};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (entryGrid[i][j].val == 0) {
                    if (BFS(entryGrid, i, j, min) == buildingNum) {
                        min[0] = Math.min(min[0], entryGrid[i][j].sum != 0 ? entryGrid[i][j].sum : Integer.MAX_VALUE);
                    }
                    for (Entry[] entries : entryGrid) {
                        for (Entry e : entries) {
                            e.startOver();
                        }
                    }
                }
            }
        }
        return min[0] != Integer.MAX_VALUE ? min[0] : -1;
    }

    private int BFS(Entry[][] entryGrid, int i, int j, int[] min) {
        Deque<Entry> queue = new LinkedList<>();
        queue.offerFirst(entryGrid[i][j]);
        entryGrid[i][j].isVisited = true;
        int count = 1;
        int buildings = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Entry cur = queue.pollLast();
                if (cur.val == 0) {
                    if (cur.x - 1 >= 0 && entryGrid[cur.x - 1][cur.y].val != 2 && !entryGrid[cur.x - 1][cur.y].isVisited) {
                        if (entryGrid[cur.x - 1][cur.y].val == 1) {
                            entryGrid[i][j].sum += count;
                            buildings++;
                        } else {
                            queue.offerFirst(entryGrid[cur.x - 1][cur.y]);
                        }
                        entryGrid[cur.x - 1][cur.y].isVisited = true;
                    }
                    if (cur.y - 1 >= 0 && entryGrid[cur.x][cur.y - 1].val != 2 && !entryGrid[cur.x][cur.y - 1].isVisited) {
                        if (entryGrid[cur.x][cur.y - 1].val == 1) {
                            entryGrid[i][j].sum += count;
                            buildings++;
                        } else {
                            queue.offerFirst(entryGrid[cur.x][cur.y - 1]);
                        }
                        entryGrid[cur.x][cur.y - 1].isVisited = true;
                    }
                    if (cur.x + 1 < entryGrid.length && entryGrid[cur.x + 1][cur.y].val != 2 && !entryGrid[cur.x + 1][cur.y].isVisited) {
                        if (entryGrid[cur.x + 1][cur.y].val == 1) {
                            entryGrid[i][j].sum += count;
                            buildings++;
                        } else {
                            queue.offerFirst(entryGrid[cur.x + 1][cur.y]);
                        }
                        entryGrid[cur.x + 1][cur.y].isVisited = true;
                    }
                    if (cur.y + 1 < entryGrid[0].length && entryGrid[cur.x][cur.y + 1].val != 2 && !entryGrid[cur.x][cur.y + 1].isVisited) {
                        if (entryGrid[cur.x][cur.y + 1].val == 1) {
                            entryGrid[i][j].sum += count;
                            buildings++;
                        } else {
                            queue.offerFirst(entryGrid[cur.x][cur.y + 1]);
                        }
                        entryGrid[cur.x][cur.y + 1].isVisited = true;
                    }
                }
            }
            count++;
        }
        return buildings;
    }

    public static void main(String[] args) {
        ShortestDistanceFromAllBuildings sd = new ShortestDistanceFromAllBuildings();
        System.out.println(sd.shortestDistance(new int[][] {{0,2,1}, {1,0,2}, {0,1,0}}));
    }
}
