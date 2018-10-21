* streams can be processed in parallel transparently,
    without you having to write any multithreaded code.
    天然支持并行计算
    ```
    划分为并行列表，交给不同的CPU
    StreamSupport.stream(spliterator(), false)

    底层用到了 forkjoin
    ```

## 4.2 流简介
* 元素序列 Sequence of elements
* 源 Source
* 数据处理操作  Data processing operations
* 流水线  Pipelining
* 内部迭代  Internal iteration

## 4.3 流与集合
* 只能遍历 消费一次  Traversable only once
* 外部迭代和内部迭代

    * 集合是外部迭代
    * 流是内部迭代

* 流操作（Intermediate 中间操作 vs. terminal operations 终端操作）
   * Intermediate 中间操作
      * filter 和 map 是两个独立的操作，合并在同一次遍历 叫做 **循环合并**
      * limit sorted distinct


   * terminal operations 终端操作
      * 终端操作会从流的流水线生成结果，其结果是任何不是流的值
      * foreach count  collect 等终端操作会返回一个非流的值，并处理流水线以返回结果

* 流中的元素是按需计算的

