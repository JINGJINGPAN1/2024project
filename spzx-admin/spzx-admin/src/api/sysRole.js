import request from '@/utils/request'
import SysRole from '@/views/system/sysRole.vue'
const base_api = '/admin/system/sysRole'

// 角色列表
export const GetSysRoleListByPage = (current, limit, queryDto) => {
  return request({
    url: `${base_api}/findByPage/${current}/${limit}`, //路径
    method: 'post', //提交方式
    //接口@RequestBody前端 data: 名称，以json格式传递
    // 接口 无@RequestBody 前端params: 名称
    data: queryDto, //其他参数
  })
}

// 角色添加
export const SaveSysRole = sysRole => {
  return request({
    url: `${base_api}/saveSysRole`, //路径
    method: 'post', //提交方式
    data: sysRole, //其他参数
  })
}

// 角色修改
export const UpdateSysRole = sysRole => {
  return request({
    url: `${base_api}/updateSysRole`, //路径
    method: 'put', //提交方式
    data: sysRole, //其他参数
  })
}
