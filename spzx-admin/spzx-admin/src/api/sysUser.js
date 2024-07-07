import request from '@/utils/request'
const base_api = '/admin/system/sysUser'

// 用户列表
export const GetSysUserListByPage = (current, limit, queryDto) => {
  return request({
    url: `${base_api}/findByPage/${current}/${limit}`, //路径
    method: 'get', //提交方式
    //接口@RequestBody前端 data: 名称，以json格式传递
    // 接口 无@RequestBody 前端params: 名称
    params: queryDto, //其他参数
  })
}

// 用户添加
export const SaveSysUser = sysUser => {
  return request({
    url: `${base_api}/saveSysUser`, //路径
    method: 'post', //提交方式
    data: sysUser, //其他参数
  })
}

// 用户修改
export const UpdateSysUser = sysUser => {
  return request({
    url: `${base_api}/updateSysUser`, //路径
    method: 'put', //提交方式
    data: sysUser, //其他参数
  })
}

// 用户删除
export const DeleteSysUser = userId => {
  return request({
    url: `${base_api}/deleteById/${userId}`, //路径
    method: 'delete', //提交方式
  })
}

// 给用户分配角色请求
export const DoAssignRoleToUser = assignRoleVo => {
  return request({
    url: `${base_api}/doAssign`, //路径
    method: 'post', //提交方式
    data: assignRoleVo, //其他参数
  })
}
