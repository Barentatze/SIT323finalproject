import { Button, Col, Image, Row, Space } from "antd";
import { Divider, Typography } from 'antd';
import { ExclamationCircleOutlined } from '@ant-design/icons'

const { Title, Paragraph } = Typography;

export default function BookDetails({ book, onAddCartItem }) {
    return <Row>
        <Col span={9}>
            <Image src={book.cover} height={500} />
        </Col>
        <Col span={15}>
            <Typography>
                <Title>{book.title}</Title>
                <Divider orientation="left">基本信息</Divider>
                <Space>
                    <Paragraph>
                        {`Author：${book.author}`}
                        <Divider type="vertical" />
                        {`Sold：${book.sales}`}
                    </Paragraph>
                </Space>
                <Divider orientation="left">Introduction</Divider>
                <Paragraph>
                    {book.description}
                </Paragraph>
                <Space direction="vertical" size="large" style={{ width: "100%" }}>
                    <div style={{ backgroundColor: "#fcfaf7", padding: "20px", width: "100%" }}>
                        <Paragraph style={{ marginBottom: 0 }} type="secondary">Promotional Price</Paragraph>
                        <div><Space>
                            <div style={{ color: "#dd3735", fontSize: "16px" }}>¥</div>
                            <div style={{ color: "#dd3735", fontSize: "30px" }}>{book.price / 100}</div>
                            <div style={{ color: "#dd3735", fontSize: "18px" }}>（30% off）</div>
                        </Space>
                        </div>
                        <div>
                            <Space>
                                <div style={{
                                    backgroundColor: "#f48484",
                                    padding: "0px 4px 0px 4px",
                                    borderRadius: "5px",
                                    color: "white"
                                }}>店铺促销</div>
                                <Paragraph style={{ marginBottom: 0 }} type="secondary">¥1 off for every ¥18 spent, ¥3 off for every ¥48 spent, ¥5 off for every ¥98 spent, ¥10 off for every ¥198 spent</Paragraph>
                            </Space>
                        </div>
                        <Space>
                            <ExclamationCircleOutlined />
                            <Paragraph style={{ marginBottom: 0 }} type="secondary">Promotions cannot be combined, Please refer to the promotions available in your shopping cart.</Paragraph>
                        </Space>
                    </div>
                    <Space>
                        <Button size="large" onClick={onAddCartItem}>Add to Cart</Button>
                        <Button type="primary" size="large">Buy Now</Button>
                    </Space>
                </Space>
            </Typography>
        </Col>
    </Row>
}