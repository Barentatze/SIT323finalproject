import { Table } from "antd";
import OrderItemList from "./order_item_list";
import { formatTime } from "../utils/time";

export default function OrderTable({ orders }) {
    const columns = [
        { title: 'Receiver', dataIndex: 'receiver', key: 'receiver', },
        { title: 'Tel', dataIndex: 'tel', key: 'tel', },
        { title: 'Address', dataIndex: 'address', key: 'address', },
        {
            title: 'Order Time', dataIndex: 'createdAt', key: 'createdAt',
            render: (time) => formatTime(time)
        },
    ];

    return <Table
        columns={columns}
        expandable={{
            expandedRowRender: (order) => (
                <OrderItemList orderItems={order.items} />
            ),
        }}
        dataSource={orders.map(order => ({
            ...order,
            key: order.id
        }))}
    />
}