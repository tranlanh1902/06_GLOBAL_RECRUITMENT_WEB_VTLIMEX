@ConfigCategory_Search 
Feature: Tìm kiếm danh mục cấu hình liên quan 

Background: Open Config Category Search Page Open suceessful 
	Given DangNhap: mở trang Đăng nhập 
	Then DangNhap: kiểm tra trang Đăng nhập mở thành công với button Login được hiển thị 
	And DangNhap: nhập dữ liệu vào các trường với dữ liệu 
		| Code/Email | Password   | 
		| "274018"   | "123456a@" |
	And DangNhap: nhấn button Đăng nhập 
	Then TrangChu: kiểm tra login thành công với acc "Đỗ Văn Đô" được hiển thị 
	When TrangChu: nhấn menu icon "Quản lý cấu hình danh mục liên quan" 
	Then QLCauHinhDanhMuc-TimKiem: kiểm tra mở trang Tìm kiếm cấu hình thành công với title "DANH SÁCH CẤU HÌNH DANH MỤC LIÊN QUAN" 
	
Scenario Outline: 01_[QL Cấu hình danh mục - Tìm kiếm] Kiểm tra tìm kiếm thành công 
	Given QLCauHinhDanhMuc-TimKiem: nhập các tiêu chí tìm kiếm 
		| Group   | Field   | Case   | Name   | Type   | CategoryRelevant   | 
		| <group> | <field> | <case> | <name> | <type> | <categoryRelevant> |
	And QLCauHinhDanhMuc-TimKiem: nhấn button Tìm kiếm 
	And QLCauHinhDanhMuc-TimKiem: lấy số bản ghi tìm kiếm UI trả về 
	And QLCauHinhDanhMuc-TimKiem: lấy số bản ghi tìm kiếm DB trả về 
		| Name   | Type   | CategoryRelevant   | 
		| <name> | <type> | <categoryRelevant> | 
	And QLCauHinhDanhMuc-TimKiem: số bản ghi tìm kiếm UI trả về bằng số bản ghi tìm kiếm DB trả về 
	And QLCauHinhDanhMuc-TimKiem: lấy danh sách kết quả tìm kiếm cột Tên cấu hình UI trả về 
	And QLCauHinhDanhMuc-TimKiem: lấy danh sách kết quả tìm kiếm cột Tên cấu hình DB trả về 
		| Name   | Type   | CategoryRelevant   | 
		| <name> | <type> | <categoryRelevant> | 
	And QLCauHinhDanhMuc-TimKiem: kiểm tra danh sách kết quả tìm kiếm cột Tên cấu hình UI trả về bằng danh sách kết quả tìm kiếm cột Tên cấu hình DB trả về 
	
	Examples: Thông tin tìm kiếm 
		| group                          | field                                                                 | case                                                 | name       | type              | categoryRelevant        | 
		| Tìm kiếm chung                 |                                                                       | Tìm kiếm mặc định                                    | ""         | ""                | ""                      | 
		| Tìm kiếm chung                 |                                                                       | Tìm kiếm không ra kết quả                            | "qưer"     | ""                | ""                      | 
		| Tìm kiếm theo tiêu chí đơn lẻ  | Tên cấu hình_Textbox                                                  | Textbox là chuỗi ký tự có space đầu cuối             | " a "      | ""                | ""                      | 
		| Tìm kiếm theo tiêu chí đơn lẻ  | Tên cấu hình_Textbox                                                  | Tìm kiếm tương đối và không phân biệt hoa thường     | "A"        | ""                | ""                      | 
		| Tìm kiếm theo tiêu chí đơn lẻ  | Tên cấu hình_Textbox                                                  | Tìm kiếm tiếng việt có dấu                           | "giám đốc" | ""                | ""                      | 
		| Tìm kiếm theo tiêu chí đơn lẻ  | Loại danh mục_Combobox                                                | Tìm kiếm với combobox = '---Tất cả---'               | ""         | ""                | ""                      | 
		| Tìm kiếm theo tiêu chí đơn lẻ  | Loại danh mục_Combobox                                                | Tìm kiếm với combobox = <1 giá trị trong combobox>   | ""         | "Loại tuyển dụng" | ""                      | 
		| Tìm kiếm theo tiêu chí đơn lẻ  | Tên danh mục_Combobox                                                 | Tìm kiếm với combobox = '---Tất cả---'               | ""         | "Loại tuyển dụng" | ""                      | 
		| Tìm kiếm theo tiêu chí đơn lẻ  | Tên danh mục_Combobox                                                 | Tìm kiếm với combobox = '<1 giá trị trong combobox>  | ""         | "Loại tuyển dụng" | "Nội bộ và Tuyển ngoài" | 
		| Tìm kiếm theo tiêu chí kết hợp | Tên cấu hình_Textbox & Loại danh mục_Combobox                         |                                                      | "a"        | "Loại tuyển dụng" | ""                      | 
		| Tìm kiếm theo tiêu chí kết hợp | Tên cấu hình_Textbox & Loại danh mục_Combobox & Tên danh mục_Combobox |                                                      | "a"        | "Loại tuyển dụng" | "Nội bộ và Tuyển ngoài" | 
		
Scenario Outline:02_[QL Cấu hình danh mục - Tìm kiếm] Kiểm tra autocomplete thành công 
	Given QLCauHinhDanhMuc-TimKiem: nhập dữ liệu trường Loại danh mục là tiền điều kiện nếu field là Danh mục liên quan 
		| Type   | Field   | 
		| <type> | <field> |
	And QLCauHinhDanhMuc-TimKiem: nhập autocomplete với dữ liệu 
		| Field   | Case   | Autocomplete   | 
		| <field> | <case> | <autocomplete> |
	And QLCauHinhDanhMuc-TimKiem: lấy danh sách kết quả tìm kiếm UI trả về 
		| Field   | 
		| <field> |
	And QLCauHinhDanhMuc-TimKiem: lấy danh sách kết quả tìm kiếm DB trả về 
		| Field   | Autocomplete   | Type   | 
		| <field> | <autocomplete> | <type> | 
	Then QLCauHinhDanhMuc-TimKiem: kiểm tra danh sách kết quả tìm kiếm trên UI trả về bằng danh sách kết quả tìm kiếm DB trả về 
	
	Examples: Thông tin kiểm tra chức năng autocomplete 
		| field         | case                                                  | autocomplete      | type       | 
		| Loại danh mục | Tìm kiếm mặc định                                     | ""                |            | 
		| Loại danh mục | Tìm kiếm không ra kết quả                             | "@#$"             |            | 
		| Loại danh mục | Tìm kiếm nhập chuỗi ký tự có space đầu cuối           | "Loại tuyển dụng" |            | 
		| Loại danh mục | Tìm kiếm tương đối và không phân biệt hoa thường      | "TUYỂN DỤNG"      |            | 
		| Loại danh mục | Tìm kiếm không phân biệt tiếng việt có dấu, không dấu | "Loai tuyen dung" |            | 
		| Tên danh mục  | Tìm kiếm mặc định                                     | ""                | "Ngôn ngữ" | 
		| Tên danh mục  | Tìm kiếm không ra kết quả                             | "@#$"             | "Ngôn ngữ" | 
		| Tên danh mục  | Tìm kiếm nhập chuỗi ký tự có space đầu cuối           | " Tiếng Pháp "    | "Ngôn ngữ" | 
		| Tên danh mục  | Tìm kiếm tương đối và không phân biệt hoa thường      | "PHÁP"            | "Ngôn ngữ" | 
		| Tên danh mục  | Tìm kiếm không phân biệt tiếng việt có dấu, không dấu | "Tieng Phap"      | "Ngôn ngữ" | 