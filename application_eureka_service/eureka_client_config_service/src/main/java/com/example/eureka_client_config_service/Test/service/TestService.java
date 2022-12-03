package com.example.eureka_client_config_service.Test.service;

public interface TestService {

    /**
     * 描述：接口类开发规范：接口类中的方法和属性不要加任何修饰符号（public 也不要加），保持代码的简洁性，并加上有效的 Javadoc 注释。
     * 尽量不要在接口里定义变量，如果一定要定义变量，肯定是与接口方法相关，并且是整个应用的基础常量。
     */

    /**
     * @Deprecated注解
     * 若某类或某方法加上该注解之后，表示此方法或类不再建议使用，调用时也会出现删除线，但并不代表不能用，
     * 只是说，不推荐使用，因为还有更好的方法可以调用。
     * @param name
     * @return
     */
    @Deprecated
    String hiService(String name);

    String hystrixService(String name);
}
