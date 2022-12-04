<!--bug 页面组件-->
<style>

</style>
<template>
    <div>
        <div>{{titleName}}</div>
        <el-table
                :data="tableData"
                style="width: 100%">
            <el-table-column
                    prop="date"
                    label="日期"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="地址">
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    module.exports = {
        name: 'bugHome',
        props: ["tree_data", "deep"],
        data() {
            return {
                titleName: "Table Name",
            };
        },
        computed: {

        },
        mounteds: {

        }
     }
</script>
