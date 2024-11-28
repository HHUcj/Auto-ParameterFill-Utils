# Auto-ParameterFill-Utils
JUNIT单元测试 接口参数按真实业务自动生成工具

    在真实的业务环境中触发接口调用，aop切面会自动记录传入的参数，并将它们序列化到resources/req下methodName.json文件中；结束接口调用前也会通过aop切面自动记录返回值Result，并序列化到resources/resp下methodName.json文件中。
    开发完一个接口后只需按功能在UI界面或postman等触发它的调用，理论上接口只要联调完成，req和resp的对象便已经持久化到json文件中，无需再额外关注。后续编写单元测试时，直接反序列化对应的json文件即可获得真实的业务参数，无需手动编写，目前功能还在持续完善中。
