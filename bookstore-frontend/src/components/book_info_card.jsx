
import { Card, Divider, Pagination, Space, Tabs } from "antd";
import { addCartItem } from "../service/cart";
import { handleBaseApiResponse } from "../utils/message";
import useMessage from "antd/es/message/useMessage";
import BookDetails from "./book_details";
import BookCommentList from "./book_comment_list";
import CommentInput from "./comment_input";
import { addBookComment } from "../service/book";

export default function BookInfoCard({
    pageIndex, sort, book, comments, onMutate, onPageChange, onSortChange
}) {
    const [messageApi, contextHolder] = useMessage();
    const handleAddCartItem = async () => {
        let res = await addCartItem(book.id);
        handleBaseApiResponse(res, messageApi);
    };

    const handleAddComment = async (comment) => {
        if (comment === "") {
            messageApi.error("The Comment Can't be empty");
            return;
        }
        let res = await addBookComment(book.id, comment);
        handleBaseApiResponse(res, messageApi, onMutate);
    };

    const tabItems = [{
        'key': 'createdTime',
        'label': 'Newest Comment'
    }, {
        'key': 'like',
        'label': 'Hottest Comment'
    }];

    return <Card className="card-container">
        {contextHolder}
        <Space direction="vertical" style={{ width: "100%" }}>
            <BookDetails book={book} onAddCartItem={handleAddCartItem} />
            <div style={{ margin: 20 }}>
                <Divider>Book Comment</Divider>
                <Tabs items={tabItems}
                    defaultActiveKey={sort}
                    onChange={sort => { onSortChange(sort) }}
                />
                <CommentInput placeholder="Send A Friendly Comment" onSubmit={handleAddComment} />
                <BookCommentList comments={comments.items} onMutate={onMutate} />
            </div>
            <Pagination
                current={pageIndex + 1}
                pageSize={5}
                total={5 * comments.total}
                onChange={onPageChange} />
        </Space>
    </Card>
}