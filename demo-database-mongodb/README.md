# demo-database-mongodb

## function
* 拷贝

`
db.getCollection("pointValueDTO").find({}).forEach(function(obj){
    var colName = 'point_value_' + obj.ev_id;
    var count = db.getCollection(colName).count({'time':obj.time});
    if(count == 0){
        db.getCollection(colName).insert(obj);
    }
})
`
* 过滤和指定字段求和

`
db.point_value_1
.aggregate([
{$match:{'time':{'$gte':1569351860719,'$lt':1569356860719}}},
{ $group: { _id : null, total: { $sum : '$value' } } }
])
`

* 查询指定字段重复的数据

`
db.getCollection('point_value_1').aggregate([{ $group: { _id : '$time', count: { $sum : 1 } } },{ $match: { count: { $gt : 1} } }])
`

* 更改数据类型
`
db.getCollection('point_value_6527').find({'_id':ObjectId("5d1d9d7f67cb440db0019fbc")}).forEach(
    function(data){
        var newValue = parseFloat(data.value);
        db.getCollection('point_value_6527').updateOne(
            {_id:data['_id']},
            {'$set':{'value': newValue}}
        )
    }
)
//查询所有的集合，过滤符合条件的集合，更改数据类型
//原生api查询并转换成对象，会严格匹配类型转换，BigDecimal对应mongo的Decimal128
db.getCollectionNames().forEach(
    function(colName){
        if(colName.startsWith('point_value_')){
            print(colName);
            db.getCollection(colName).find().forEach(
                function(pointData){
                    //转Double用parseFloat，转BigDecimal用NumberDecimal
                    var newValue = NumberDecimal(pointData.value);
                    var newOriginalValue = NumberDecimal(pointData.original_value);
                    db.getCollection(colName).updateOne(
                        {_id:pointData['_id']},
                        {'$set':{'value': newValue,'original_value': newOriginalValue}}
                    )
                }
            )
        }
    }
)
`
* 查询值为NaN的列
`
db.getCollection(colName).find({'value':NaN})
`

* 存储过程，即函数function，使用这种方法执行function需要执行账户有权限
`
db.system.js.save({
    _id:'change_value_type',
    value:function() {
        var collectionNames = db.getCollectionNames();
        for(collectionName in collectionNames){
            if(!collectionName.startsWith('point_value_')){
                continue;
            }
            db.getCollection(collectionName).find().forEach(
                function(data){
                    var newValue = parseFloat(data.value);
                    db.getCollection(colName).updateOne(
                        {_id:data['_id']},
                        {'$set':{'value': newValue}}
                    )
                }
            )
        }
}
})
db.eval('change_value_type()')
`