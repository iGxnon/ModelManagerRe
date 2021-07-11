# ModelManagerRe

### Download

<a href="https://github.com/iGxnon/ModelManagerRe/releases/latest" alt="Latest release">
    <img src="https://img.shields.io/github/v/release/iGxnon/ModelManagerRe" alt="Latest release">
</a>

### What is it?

> These following introductions depend on Google Translate, forgive my poor English :(

> `ModelManagerRe` is a `reset version` of the front-end plug-in `ModelManage(r)`
>
> It has the function of parsing (1.10.0) `old version` and (1.12.0) `new version` of bedrock model
>
> Use `Gson` to parse JSON string into POJO object for easy operation
>
> Sequence POJO objects into JSON strings for direct use, which is convenient for developers to build JSON directly

### What can it do for developers?

> 1. Parse minecraft block architecture and construct JSON model string (Not added yet) :(
> 2. Through the operation of POJO objects, we can quickly realize the change of entity model, such as the change of UV
> 3. Use `ModelManager.getModel(String index):Skin` can get a Skin object quickly

### What can it do for servers?

> 1. Manage the server's models
> 2. Load models as server startup, other plug-ins denpend this maby save a little bit of performance? :)

### How to use?

1. For Server operators: 

   > **You need to use version 2.6-release or above and config eng language(edit config.yml `language: eng`)**

   > Put `*.jar` file into `plugins` and start up, it can create a `ModelManagerRe` folder, you need to perpare a folder which contains 2 files (a `*.png` and a `*.json`) , the last step is throwing the folder you prepared into `ModelManagerRe` folder and restart your server. When you see `xx(folder name) is loaded!` on your console panel, it works.

2. For developers:

   > Please see the developer api doc

## Developer API Doc:

1. static function of class ModelManagerRe

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
   
   ```

2. NewManager and OldManager both implement `Manager`

   ```java
   public interface Manager<T> {
   
       // retrun Identifier(GeometryName)
       String getMainIdentifier();
   
       // return Model POJO Object
       T getMainModel();
   }
   ```

3. Various POJO object levels

   > Model
   >
   > > Description
   >
   > > Bone
   > >
   > > > Cube
   
4. Cube

   ```java
   	// After obtaining the cube of a bone, you can change the following properties. Finally, you can use ModelManagerRe.GetJsonString()
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

   

