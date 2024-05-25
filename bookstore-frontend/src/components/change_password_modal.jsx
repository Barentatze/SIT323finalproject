import { Button, Form, Input, Modal } from "antd";
import React from "react";
import useMessage from "antd/es/message/useMessage";
import { handleBaseApiResponse } from "../utils/message";
import { changePassword } from "../service/user";

const { Password } = Input;
export default function ChangePasswordModal({
    onOk,
    onCancel }) {
    const [form] = Form.useForm();
    const [messageApi, contextHolder] = useMessage();

    const handleSubmit = async ({ password, confirm }) => {
        if (!password || !confirm) {
            messageApi.error("Please enter the full detail!");
            return;
        }
        if (password !== confirm) {
            messageApi.error("The new and confirm passwords are different");
            return;
        }
        let request = {
            password
        }
        let res = await changePassword(request);
        handleBaseApiResponse(res, messageApi, onOk);
    };

    return (
        <Modal
            title={"Change Password"}
            open
            onOk={onOk}
            onCancel={onCancel}
            footer={null}
            width={800}
        >
            {contextHolder}
            <Form
                form={form}
                layout="vertical"
                onFinish={handleSubmit}
                preserve={false}
            >
                <Form.Item
                    name="password"
                    label="New Password"
                    required
                >
                    <Password placeholder="Please Enter New Password" />
                </Form.Item>
                <Form.Item
                    name="confirm"
                    label="Confirm New Password"
                    required
                >
                    <Password placeholder="Enter Confirm Password" />
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit">
                        Submit
                    </Button>
                </Form.Item>
            </Form>
        </Modal>
    );
};