# API设计的安全方法

### Token令牌

- API Token(接口令牌): 用于访问不需要用户登录的接口，如登录、注册、一些基本数据的获取等。获取接口令牌需要拿appId、timestamp和sign来换，sign=加密(timestamp+appId+key)
- USER Token(用户令牌): 用于访问需要用户登录之后的接口，如：获取我的基本信息、保存、修改、删除等操作。获取用户令牌需要拿用户名和密码来换
- timestamp: 时间戳，是客户端调用接口时对应的当前时间戳，时间戳用于防止DoS攻击。
- nonce：随机值，是客户端随机生成的值，作为参数传递过来，随机值的目的是增加sign签名的多变性。随机值一般是数字和字母的组合，6位长度，随机值的组成和长度没有固定规则。
- sign: 一般用于参数签名，防止参数被非法篡改，最常见的是修改金额等重要敏感参数， sign的值一般是将所有非空参数按照升续排序然后+token+timestamp+nonce(随机数)拼接在一起，然后使用某种加密算法进行加密，作为接口中的一个参数sign来传递，也可以将sign放到请求头中。

### 使用流程

1.接口调用方(客户端)向接口提供方(服务器)申请接口调用账号，申请成功后，接口提供方会给接口调用方一个appId和一个key参数

2.客户端携带参数appId、timestamp、sign去调用服务器端的API token，其中sign=加密(timestamp + appId + key)

3.客户端拿着api_token 去访问不需要登录就能访问的接口

4.当访问用户需要登录的接口时，客户端跳转到登录页面，通过用户名和密码调用登录接口，登录接口会返回一个usertoken, 客户端拿着usertoken 去访问需要登录才能访问的接口

sign的作用是防止参数被篡改，客户端调用服务端时需要传递sign参数，服务器响应客户端时也可以返回一个sign用于客户度校验返回的值是否被非法篡改了。客户端传的sign和服务器端响应的sign算法可能会不同。