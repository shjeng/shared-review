export default interface Pageable<T> {
    content: T[];
    pageNumber: number; // 현재 페이지
    pageSize: number; // 페이지 사이즈?
    sort: {
        empty: boolean;
        sorted: boolean;
        unsorted: boolean;
    },
    offset: number;
    paged: boolean;
    unpaged: boolean
}