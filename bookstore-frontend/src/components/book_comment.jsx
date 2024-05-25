import { List, Space } from "antd";
import UsernameAvatar from "./username_avatar";
import LikeButton from "./like_button";
import { formatTime } from "../utils/time";
import CommentInput from "./comment_input";
import { likeComment, replyComment, unlikeComment } from "../service/comment";
import { handleBaseApiResponse } from "../utils/message";
import useMessage from "antd/es/message/useMessage";

export default function BookComment({ comment, isReplying, onReply, onMutate }) {
    const content = comment.content;
    const [messageApi, contextHolder] = useMessage();
    const replyMessage = comment.reply ? `Reply ${comment.reply}：` : '';
    const handleReply = (e) => {
        e.preventDefault();
        onReply();
    };

    const handleSubmitReply = async (content) => {
        if (content === "") {
            messageApi.error("The reply can't be empty！");
            return;
        }
        let res = await replyComment(comment.id, content);
        handleBaseApiResponse(res, messageApi, onMutate);
    };

    const handleLikeComment = async () => {
        let res = await likeComment(comment.id);
        handleBaseApiResponse(res, messageApi);
        return res.ok;
    };

    const handleUnlikeComment = async () => {
        let res = await unlikeComment(comment.id);
        handleBaseApiResponse(res, messageApi);
        return res.ok;
    };

    const contentComponent = <Space direction="vertical" style={{ width: '100%' }}>
        <p style={{ fontSize: 16, color: "black", margin: 0 }}>{replyMessage}{content}</p>
        <Space>
            {formatTime(comment.createdAt)}
            <LikeButton defaultNumber={comment.like} liked={comment.liked}
                onLike={handleLikeComment}
                onUnlike={handleUnlikeComment}
            />
            <a style={{ color: "grey", fontSize: 14 }}
                onClick={handleReply}>Reply
            </a>
        </Space>
        {isReplying && <CommentInput placeholder={`Reply ${comment.username}：`}
            onSubmit={handleSubmitReply}
            autoFocus
        />}
    </Space>

    return <>
        {contextHolder}
        <List.Item key={comment.id}>
            <List.Item.Meta
                avatar={<UsernameAvatar username={comment.username} />}
                title={<div style={{ color: "grey" }}>{comment.username}</div>}
                description={contentComponent}
            />
        </List.Item>
    </>
}