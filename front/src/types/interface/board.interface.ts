import User from "./user.interface";
import Tag from "./tag.interface";

export default interface Board {
  boardId: bigint;
  title: string;
  content: string;
  category: string;
  viewCount: number;
  commentCount: number;
  favoriteCount: number;
  updateDateTime: string;
  user: User;
  writeDateTime: string;
  tags: Tag[]
}
