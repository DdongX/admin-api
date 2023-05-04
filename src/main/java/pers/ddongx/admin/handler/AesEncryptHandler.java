package pers.ddongx.admin.handler;

import cn.hutool.core.text.CharSequenceUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import pers.ddongx.admin.util.AESUtil;

import java.sql.*;

/**
 * Description: 字段加密
 *
 * @author Ddong
 * @since 2023/5/3 18:54
 */
public class AesEncryptHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setNull(i, Types.VARCHAR);
        } else if (CharSequenceUtil.isEmpty(parameter)) {
            ps.setString(i, "");
        } else {
            ps.setString(i, AESUtil.encrypt(parameter));
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        return CharSequenceUtil.isEmpty(columnValue) ? columnValue : AESUtil.decrypt(columnValue);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return CharSequenceUtil.isEmpty(columnValue) ? columnValue : AESUtil.decrypt(columnValue);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return CharSequenceUtil.isEmpty(columnValue) ? columnValue : AESUtil.decrypt(columnValue);
    }

}
