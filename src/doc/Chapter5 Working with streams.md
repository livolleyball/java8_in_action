## 构建流
* 值
* 数组
* 文件
* 函数：创建无限流
   * 迭代
   * 生成 (对象)

## 筛选和切片
* 使用谓词筛选
* 筛选各异的元素
* 截短流
* 跳过元素

## 映射
* 一一映射
* 流的扁平化 flatMap

## 查找和匹配
* 检查谓词是否至少匹配一个元素 anyMatch
* 检查谓词是否匹配所有元素
> *短路* 有些操作不需要处理整个流程就能得到结果。(allmatch anyMatch noneMatch findFirst findAny)

* 查找元素 findAny
* 查找第一个元素 findFirst
> findAny findFirst 的区别，前者是并行的。后者串行，且关注元素所在的位置
> Optional<T> 是一个容器类，代表一个值存在或不存在。