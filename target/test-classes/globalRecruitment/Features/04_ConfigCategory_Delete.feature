@ConfigCategory_Delete 
Feature: Xoá cấu hình danh mục liên quan 

Background: Hiện thị alert xác nhận xoá thành công 
	Given DangNhap: mở trang Đăng nhập 
	Then DangNhap: kiểm tra trang Đăng nhập mở thành công với button Login được hiển thị 
	And DangNhap: nhập dữ liệu vào các trường với dữ liệu 
		| Code/Email | Password   | 
		| "274018"   | "123456a@" |
	And DangNhap: nhấn button Đăng nhập 
	Then TrangChu: kiểm tra login thành công với acc "Đỗ Văn Đô" được hiển thị 
	When TrangChu: nhấn menu icon "Quản lý cấu hình danh mục liên quan" 
	Then QLCauHinhDanhMuc-TimKiem: kiểm tra mở trang Tìm kiếm cấu hình thành công với title "DANH SÁCH CẤU HÌNH DANH MỤC LIÊN QUAN" 
	When QLCauHinhDanhMuc-TimKiem: nhập tiêu chí tìm kiếm Tên cấu hình cần xoá vừa thêm hoặc sửa thành công 
	And QLCauHinhDanhMuc-TimKiem: nhấn button Tìm kiếm 
	Then QLCauHinhDanhMuc-TimKiem: kiểm tra số bản ghi trả về thành công là 1 
	When QLCauHinhDanhMuc-TimKiem: nhấn icon Xoá
	
Scenario: 01_[QL Cấu hình danh mục - Xoá] Kiểm tra Xoá cấu hình thành công 
	Given QLCauHinhDanhMuc-TimKiem: nhấn button OK xác nhận xoá 
	Then QLCauHinhDanhMuc-TimKiem: kiểm tra xoá thành công với toast message hiển thị với nội dung "Thao tác thực hiện thành công" 
	And QLCauHinhDanhMuc-Xoa: kiểm tra update thành công DB bảng CAT_GROUP 1 row với dữ liệu đã xoá 
	And QLCauHinhDanhMuc-Xoa: kiểm tra update thành công DB bảng CATEGORY_GROUP 2 row với dữ liệu đã xoá