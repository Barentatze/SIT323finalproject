import React from "react";

import {
    LockOutlined,
    UserOutlined,
} from '@ant-design/icons';
import { LoginFormPage, ProFormText } from '@ant-design/pro-components';
import useMessage from "antd/es/message/useMessage";
import { Link, useNavigate } from "react-router-dom";
import { BasicLayout } from "../components/layout";
import { login } from "../service/login";
import { handleBaseApiResponse } from "../utils/message";

const LoginPage = () => {
    const [messageApi, contextHolder] = useMessage();
    const navigate = useNavigate();

    const onSubmit = async (values) => {
        let email = values['username'];
        let password = values['password'];

        let res = await login(email, password);
        handleBaseApiResponse(res, messageApi, () => navigate("/"));
    };

    return (
        <BasicLayout>
            {contextHolder}
            <LoginFormPage
                backgroundImageUrl={process.env.PUBLIC_URL + 'login.png'}
                logo={process.env.PUBLIC_URL + '/logo.webp'}
                title="Book Store"
                subTitle="Book Store"
                onFinish={onSubmit}
                style={{ height: "80vh" }}
            >
                <ProFormText
                    name="username"
                    fieldProps={{
                        size: 'large',
                        prefix: <UserOutlined className={'prefixIcon'} />,
                    }}
                    placeholder={'Please enter user name'}
                    rules={[
                        {
                            required: true,
                            message: 'Please enter User name ',
                        },
                    ]}
                />
                <ProFormText.Password
                    name="password"
                    fieldProps={{
                        size: 'large',
                        prefix: <LockOutlined className={'prefixIcon'} />,
                    }}
                    placeholder={'Password'}
                    rules={[
                        {
                            required: true,
                            message: 'Please Enter Password',
                        },
                    ]}
                />
                <div
                    style={{
                        marginBlockEnd: 24,
                    }}
                >
                    <Link to='/register'>Register</Link>
                    <a
                        style={{
                            float: 'right',
                        }}
                        href="#/"
                    >
                        Forget Password
                    </a>
                </div>
            </LoginFormPage>
        </BasicLayout>
    );
};
export default LoginPage;