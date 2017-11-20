## Usage
**Step 1**. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
**Step 2**. Add the dependency
```
dependencies {
    compile 'com.github.lizhengxian1991.zxmessager:event-lib:1.0.0'
    annotationProcessor 'com.github.lizhengxian1991.zxmessager:processor:1.0.0'
}
```
**Step 2**. Set the IndexPackage and enable includeCompileClasspath
```
android {
    ...
    defaultConfig {
        ...
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [ IndexPackage : '<your package name>' ]
                includeCompileClasspath = true
            }
        }
    }
}
```
## Todo
下一步：

    ~~ 1. post 无参方法 ~~
    ~~ 2. Subscribe 指定线程 ~~
    ~~ 3. Processor优化，直接生成java class，判断是否BaseController子类 ~~
    ~~ 4. 合并初始化方法，只留一个初始化方法 ~~ 
    5. 一对多Notify
    ~~ 6. int 用 Integer代替 ~~
    ~~ 7. DebugMode输出日志 ~~
    8. 接入jit pack