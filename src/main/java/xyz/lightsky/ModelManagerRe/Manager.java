package xyz.lightsky.ModelManagerRe;

public interface Manager<T> {

    // 返回模型的Identifier，即GeometryName
    String getMainIdentifier();

    // 返回模型的Model POJO对象
    T getMainModel();
}
