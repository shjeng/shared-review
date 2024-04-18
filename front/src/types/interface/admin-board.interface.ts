export default interface AdminBoard {
  writeDateTime: string;
  userDto: any;
  selected: boolean;
  boardId: number;
  title: string;
  content: string;
  category: string;
  viewCount: number;
}
