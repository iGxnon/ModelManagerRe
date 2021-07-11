# ModelManagerRe

<a href="https://github.com/iGxnon/ModelManagerRe/README_EN.md" alt="Latest release">
    <img src="https://img.shields.io/badge/README-English-green">
</a>

### 安装

<a href="https://github.com/iGxnon/ModelManagerRe/releases/latest" alt="Latest release">
    <img src="https://img.shields.io/github/v/release/iGxnon/ModelManagerRe" alt="Latest release">
</a>


### 这是什么

> `ModelManagerRe`是前置插件`ModelManage(r)`的**重置版**
>
> 具备了解析(1.10.0)旧版和(1.12.0)新版基岩版模型的功能
>
> 秉承了`ModelManage(r)`的优点
>
> 使用Gson将json字符串解析成POJO对象以便于操作
>
> 将POJO对象序列化成json字符串直接使用，便于开发者直接构建json

### 应用场景

> 1. 解析Minecraft方块建筑，快速构建json模型字符串 (尚未加入,请别的开发者加入或者本人以后更新)
>2. 快捷实现实体模型的变化，如rotation的改变
> 3. 管理服务器模型，即拿即用
> 4. 如果其他插件使用本插件加载模型,可能会节省一点性能?

### 如何使用

> 1. 对于服务器运营者:
>
>    将jar文件放置于plugins下，在生成的文件夹里面放入模型文件夹（包含一个png文件(分辨率不能超过128*128)和一个json文件），重启服务器即可，此插件将作为众多插件的前置使用，可方便服主管理模型，提高服务器运行效率
>
> 2. 对于开发者:
>
>    请看开发者文档

### 开发者文档

1. ModelManagerRe类的静态方法

   ```java
   /**
   
   * 
    * @param jsonPath provide a path
    * @return NewManager or OldManager
    * @throws IOException 
      */
      public static Manager getModelManager(String jsonPath) throws IOException
   
    /**
   
     * 
     * @return Models list
       */
        public static LinkedHashMap<String, Skin> getModels()
   
    /**
   
     * 
     * @param name index
     * @return Model
       */
        public static Skin getModel(String name)
   
    /**
   
     * 
     * @param json json data
     * @return NewManager or OldManager
       */
        public static Manager getFromJsonString(String json)
   
    /**
   
     * 
     * @param manager NewManager or OldManager
     * @return json data
       */
        public static String getJsonString(Manager manager)

2. NewManager和OldManager均实现了`Manager`接口

   ```java
   public interface Manager<T> {
   
       // 返回模型的Identifier，即GeometryName
       String getMainIdentifier();
   
       // 返回模型的Model POJO对象
       T getMainModel();
   }
   ```

3. 各种POJO对象层级

   > Model
   >
   > > Description
   >
   > > Bone
   > >
   > > > Cube

4. Cube

   ```java
   	// 获取到某个bone的cube后，即可以对下面的属性进行更改，最后使用ModelMangerRe.getJsonString()即可
       public void setMirror(boolean mirror) {
           this.mirror = mirror;
       }
   
       public void setUv(int x, int y) {
           this.uv = Arrays.asList(x, y);
       }
   
       public void setRotation(double x, double y, double z) {
           this.rotation = Arrays.asList(x, y, z);
       }
   
       public void setPivot(double x, double y, double z) {
           this.pivot = Arrays.asList(x, y, z);
       }
   
       public void setOrigin(double x, double y, double z) {
           this.origin = Arrays.asList(x, y, z);
       }
   
       public void setSize(double x, double y, double z) {
           this.size = Arrays.asList(x, y, z);
       }
   ```

   
