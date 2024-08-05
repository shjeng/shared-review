import User from "./user.interface";
import Tag from "./tag.interface";
import Category from "./category.interface";

export default interface Board {
  boardId: bigint;
  title: string;
  content: string;
  category: Category;

  viewCount: number;
  commentCount: number;
  favoriteCount: number;
  updateDateTime: string;
  user: User;
  writeDateTime: string;
  tags: Tag[];
}
