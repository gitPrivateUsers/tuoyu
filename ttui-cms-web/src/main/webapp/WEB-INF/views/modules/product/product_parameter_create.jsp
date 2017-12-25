<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/layouts/base.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>创建商品参数 - 拓语网络</title>
    <link rel="stylesheet" href="${ctxsta}/common/icheck/flat/green.css"/>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>创建商品参数</h5>
                    <div class="ibox-tools"><a class="collapse-link"><i class="fa fa-chevron-up"></i></a> <a
                            class="close-link"><i class="fa fa-times"></i></a></div>
                </div>
                <div class="ibox-content">
                    <form id="form" class="form-horizontal" action="${ctx}/product/detail/createProductParameter" data-method="post">
                       <div class="form-group m-t">
                            <label class="col-sm-2 col-xs-offset-1 control-label">商品ID：</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" readonly="readonly" name="productId" value="${productId}">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 col-xs-offset-1 control-label">参数名称：</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-xs-offset-1 control-label">参数值：</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="value">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 col-xs-offset-1 control-label">状态：</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="status">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 col-xs-offset-1 control-label">排序：</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" name="sort">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-12 text-center">
                                <button class="btn btn-primary" type="submit">提交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<myfooter>
    <!-- 自定义js -->
    <script src="${ctxsta}/cms/js/productParameterList.js"></script>
</myfooter>
</body>
</html>
