import { useEffect, useState } from "react";

// countPerPage : 한페이지당 몇개 보여줄지
const usePagination = (countPerPage: number) => {
  //  state: 전체 item 수    //
  const [countPerItem,setCountPerItem] = useState<number>(10);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalCount, setTotalCount] = useState<number>(0);
  const [section, setSection] = useState<number>(0);
  const [start, setStart] = useState<number>();
  const [end, setEnd] = useState<number>();
  //  state: 현재 페이지 번호 상태   //
  // 지금 몇번째 페이지에 있는지에 대한걸 저장할 상태

  const set = () => {
    const totalPage = totalCount / countPerItem;
    setSection(currentPage / countPerPage);
    const startNumber = section * countPerPage + 1;
    setStart(startNumber);
    const endNumber = totalPage > section * countPerPage ? section * countPerPage : totalPage;
    setEnd(endNumber);
  }

  useEffect(() => {
    set();
  }, [currentPage]);

  return {
    setCurrentPage, setTotalCount, setCountPerItem
  }

}


export default usePagination;
