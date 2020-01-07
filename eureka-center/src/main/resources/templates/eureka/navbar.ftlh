<h1>服务说明</h1>
<div class="row">
    <div class="col-md-6">
        <div style="margin-left: 30px;margin-top: 20px;">
            <h3>如何使用</h3>
            <p>
                如何搭建并使用Eureka，博客地址为：
                <br>
                <span style="color: #357ebd;font-weight: bold;">https://blog.csdn.net/Mrs_chens/article/details/103776838</span>
            </p>
            <br>
            <h3>注意事项</h3>
            <div style="color:red;">
                <li>由于本Eureka Server为公益应用，旨在帮助Spring Cloud的开发者进行学习使用。</li>
                <li>可能存在运行不稳定而重启等风险，请勿将该注册中心用于您的生产配置中。</li>
                <li>同时，为了保障大家使用的顺畅，使用者请勿对本注册中心进行压力测试。</li>
            </div>
            <div style="margin-top: 20px;">
                P.S. 如在学习与使用过程中遇到困难，就来<a href="https://blog.csdn.net/Mrs_chens" target="_blank">陈永佳的博客</a>探讨交流。
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div style="margin-left: 30px;margin-top: 20px;">
            <h3>参考项目地址</h3>
            <div>
                <li><a href="https://blog.csdn.net/mrs_chens/category_9632982.html" target="_blank">Spring Cloud Hoxton 基础教程</a></li>
                <li><a href="https://github.com/chenxiban/Family" target="_blank">Spring Cloud Hoxton 版本案例项目...</a></li>
            </div>
            <br>
            <h3>关注我</h3>
            <div>
                <li class="foot-li">
                    <a href="https://github.com/chenxiban" target="_blank">Github</a>
                </li>
                <li class="foot-li">
                    <a href="https://blog.csdn.net/Mrs_chens" target="_blank">CSDN</a>
                </li>
                <li class="foot-li">
                    <a href="#" target="_blank">简书（暂无）</a>
                </li>
                <li class="foot-li">
                    <a href="#" target="_blank">知乎（暂无）</a>
                </li>
            </div>

        </div>
    </div>
    <div class="col-md-3">
        <div style="margin-left: 30px;margin-top: 20px;">
            <h3>微信号</h3>
            <img style="width: 80px;" src="../img/weixin.png">
            <br><br>
            <h3>我的博客</h3>
            <div>
                <li class="foot-li">
                    <a href="https://blog.csdn.net/mrs_chens/category_9632982.html" target="_blank">陈永佳的博客</a>
                </li>
            </div>
        </div>
    </div>
    <div class="col-md-12" style="text-align: center;">
        <div id="cyReward" role="cylabs" data-use="reward"></div>
    </div>
</div>
<br>
<hr>

<h1>系统状态</h1>
<div class="row">
    <div class="col-md-6">
        <table id='instances' class="table table-condensed table-striped table-hover">
            <#if amazonInfo??>
                <tr>
                    <td>EUREKA 服务器</td>
                    <td>AMI: ${amiId!}</td>
                </tr>
                <tr>
                    <td>地址</td>
                    <td>${availabilityZone!}</td>
                </tr>
                <tr>
                    <td>实例id</td>
                    <td>${instanceId!}</td>
                </tr>
            </#if>
            <tr>
                <td>环境</td>
                <td>${environment!}</td>
            </tr>
            <tr>
                <td>数据中心</td>
                <td>${datacenter!}</td>
            </tr>
        </table>
    </div>
    <div class="col-md-6">
        <table id='instances' class="table table-condensed table-striped table-hover">
            <tr>
                <td>当前时间</td>
                <td>${currentTime}</td>
            </tr>
            <tr>
                <td>正常运行时间</td>
                <td>${upTime}</td>
            </tr>
            <tr>
                <td>Lease expiration enabled</td>
                <td>${registry.leaseExpirationEnabled?c}</td>
            </tr>
            <tr>
                <td>更新阈值</td>
                <td>${registry.numOfRenewsPerMinThreshold}</td>
            </tr>
            <tr>
                <td>更新(最后一分钟)</td>
                <td>${registry.numOfRenewsInLastMin}</td>
            </tr>
        </table>
    </div>
</div>

<#if isBelowRenewThresold>
    <#if !registry.selfPreservationModeEnabled>
        <h4 id="uptime"><font size="+1" color="red"><b>续订低于阈值。自我保存模式被关闭。当网络或其他问题发生时，这可能无法保护实例过期。</b></font></h4>
    <#else>
        <h4 id="uptime"><font size="+1" color="red"><b>紧急！EUREKA可能错误地声明实例已经启动，而实际上它们并没有。续订低于阈值，因此不会为了安全而终止实例。</b></font></h4>
    </#if>
<#elseif !registry.selfPreservationModeEnabled>
    <h4 id="uptime"><font size="+1" color="red"><b>自我保护模式被关闭。当网络或其他问题发生时，这可能无法保护实例过期。</b></font></h4>
</#if>

<h1>DS Replicas</h1>
<ul class="list-group">
    <#list replicas as replica>
        <li class="list-group-item"><a href="${replica.value}">${replica.key}</a></li>
    </#list>
</ul>