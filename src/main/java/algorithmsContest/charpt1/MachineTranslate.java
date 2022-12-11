package algorithmsContest.charpt1;

import edu.princeton.cs.Queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 1.2 队列
 *
 * @className: MachineTranslate
 * @description: 例1.2 机器翻译
 * 问题描述: 内存中有M 个单元, 每个单元存储一个单词和意译。每当将一个新单词存入内存前, 如果当前内存中已存入的单词
 * 数不超过 M-1, 会将新单词存入一个未使用的内存单元； 若内存中已存入M个单词， 会清空最早进入内存的那个单词， 腾出
 * 单元存放新单词。
 *
 * 假设一片英文文章的长度为N 个单词。 给定这篇待译文章，翻译软件需要去外存查找多少次词典？ 假设在开始翻译前，内存中
 * 没有任何单词。
 *
 * 输入: 共两行，每行中两个数之间用一个空格隔开。第1行输入两个正整数 M 和 N, 代表内存容量和文章的长度。 第2行输入
 * N个非负整数， 按照文章的顺序，每个数(大小不超过1000) 代表一个英文单词。文章中两个单词是同一个单词，当且仅当它们
 * 对应的非负整数相同。
 *
 * 输出: 一个整数， 为软件需要查词典的次数。
 *
 * @version: 1.0
 * @author: minsky
 * @date: 2022/12/4
 */
public class MachineTranslate {

    public static void main(String[] args) {
        MachineTranslate instance = new MachineTranslate();
//        System.out.println(instance.solution1(10, 12));
        System.out.println(instance.solution2(10, 12));
    }

    /**
     * 使用队列模拟内存
     * 使用数组作为hash 存储单词是否出现过
     *
     * @param m
     * @param n
     * @return
     */
    public int solution1(int m, int n){
        Deque<Integer> memory = new LinkedList<>();
        int[] hash = new int[1005];
        int cnt = 0;
        Scanner scan = new Scanner(System.in);
        while(n-- > 0){
            int word = scan.nextInt();
            if(hash[word] == 0){
                cnt++;
                hash[word] = 1;
                memory.addLast(word);
                if(memory.size() > m){
                    int pop = memory.removeFirst();
                    hash[pop] = 0;
                }
            }
        }
        return cnt;
    }


    /**
     * 使用自定义循环队列实现
     * @param m
     * @param n
     * @return
     */
    public int solution2(int m, int n){
        int N = 1005;
        int[] hash = new int[N];
        CyclicQueue queue = new CyclicQueue(N);

        Scanner scanner = new Scanner(System.in);
        int cnt = 0;
        while(n-- > 0){
            int word = scanner.nextInt();
            if(hash[word] == 0){
                cnt ++;
                hash[word] = 1;
                queue.push(word);
                if(queue.size() > m){
                    Integer pop = queue.pop();
                    hash[pop] = 0;
                }
            }
        }
        return cnt;
    }
    class CyclicQueue{
        int data[];
        int size;
        int head, tail;
        public CyclicQueue(int size){
            this.size = size;
            data = new int[this.size];
            head = 0; tail = 0;
        }
        Integer size(){
            return (tail - head + size) % size;
        }

        Boolean empty(){
            return size() == 0 ? true : false;
        }

        Boolean push(int e){
            if((tail + 1) % size == head) return false;
            data[tail] = e;
            tail = (tail + 1) % size;
            return true;
        }
        // 若队列为空, pop 返回-1
        Integer pop(){
            if(head == tail) return null;
            int e = data[head];
            head = (head + 1) % size;
            return e;
        }

        Integer front(){ return data[head];}
    }




}
