import { Button, Form, Input, Modal } from "antd";
import React from "react";
import useMessage from "antd/es/message/useMessage";
import { placeOrder } from "../service/order";
import { handleBaseApiResponse } from "../utils/message";

const { TextArea } = Input;
export default function PlaceOrderModal({
    selectedItems,
    onOk,
    onCancel }) {
    const [form] = Form.useForm();
    const [messageApi, contextHolder] = useMessage();

    const handleSubmit = async ({ address, receiver, tel }) => {
        if (!address || !receiver || !tel) {
            messageApi.error("请填写完整信息！");
            return;
        }
        let orderInfo = {
            address,
            receiver,
            tel,
            itemIds: selectedItems.map(item => item.id)
        }
        let res = await placeOrder(orderInfo);
        handleBaseApiResponse(res, messageApi, onOk);
    };

    return (
        <Modal
            title={"Confirm Order"}
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
                    name="receiver"
                    label="receiver"
                    required
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    name="tel"
                    label="Tel"
                    required
                >
                    <Input />
                </Form.Item>
                <Form.Item
                    name="address"
                    label="Address"
                    required
                >
                    <TextArea rows={2} maxLength={817} />
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