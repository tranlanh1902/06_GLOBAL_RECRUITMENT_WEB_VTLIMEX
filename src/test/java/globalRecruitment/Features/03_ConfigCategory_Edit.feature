@ConfigCategory_Edit 
Feature: Sửa cấu hinh danh mục liên quan 

Background: Mở trang Sửa cấu hình danh mục thành công 
	Given DangNhap: mở trang Đăng nhập 
	Then DangNhap: kiểm tra trang Đăng nhập mở thành công với button Login được hiển thị 
	And DangNhap: nhập dữ liệu vào các trường với dữ liệu 
		| Code/Email | Password   | 
		| "274018"   | "123456a@" |
	And DangNhap: nhấn button Đăng nhập 
	Then TrangChu: kiểm tra login thành công với acc "Đỗ Văn Đô" được hiển thị 
	When TrangChu: nhấn menu icon "Quản lý cấu hình danh mục liên quan" 
	Then QLCauHinhDanhMuc-TimKiem: kiểm tra mở trang Tìm kiếm cấu hình thành công với title "DANH SÁCH CẤU HÌNH DANH MỤC LIÊN QUAN" 
	When QLCauHinhDanhMuc-TimKiem: nhập tiêu chí tìm kiếm Tên cấu hình cần sửa vừa thêm hoặc sửa thành công 
	And QLCauHinhDanhMuc-TimKiem: nhấn button Tìm kiếm 
	Then QLCauHinhDanhMuc-TimKiem: kiểm tra số bản ghi trả về thành công là 1 
	When QLCauHinhDanhMuc-TimKiem: nhấn icon Sửa 
	Then QLCauHinhDanhMuc-Sua: kiểm tra mở trang Sửa cấu hình thành công với title "SỬA CẤU HÌNH" 
	
Scenario Outline:01_[QL Cấu hình danh mục - Sửa] Kiểm tra Sửa cấu hình thành công 
	When QLCauHinhDanhMuc-Sua: nhập dữ liệu màn hình Sửa cấu hình với các dữ liệu sau 
		| Name   | NameEnglish   | Type   | CategoryRelevant1   | CategoryRelevant2   | 
		| <name> | <nameEnglish> | <type> | <categoryRelevant1> | <categoryRelevant2> |
	When QLCauHinhDanhMuc-Sua: nhấn button Lưu 
	And QLCauHinhDanhMuc-Sua: nhấn button OK xác nhận lưu 
	Then QLCauHinhDanhMuc-TimKiem: kiểm tra sửa thành công với toast message hiển thị với nội dung "Thao tác thực hiện thành công" 
	And QLCauHinhDanhMuc-Sua: kiểm tra update thành công DB bảng CAT_GROUP 1 row với dữ liệu đã sửa 
	And QLCauHinhDanhMuc-Sua: kiểm tra update thành công DB bảng CATEGORY_GROUP 2 row với dữ liệu đã sửa 
	When QLCauHinhDanhMuc-Sua: lấy dữ liệu column code của Cấu hình danh mục vừa sửa trong bảng CAT_GROUP 
	
	Examples: Thông tin kiểm tra Sửa cấu hình danh mục thành công 
		| case                                                                                                                     | name                                                                                                   | nameEnglish                                                                                            | type                   | categoryRelevant1 | categoryRelevant2 | 
		| Kiểm tra cập nhật thành công khi nhập các trường textbox = maxlength (Tên cấu hình = 100, Tên cấu hình(Tiếng anh) = 100) | "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111113" | "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111113" | "Danh sách thị trường" | "Việt Nam"        | "Myanmar"         | 
		| Kiểm tra cập nhật thành công khi nhập các trường bắt buộc khi text box chứa space đầu cuối                               | " Thị trường1 "                                                                                        | " Thị trường english1 "                                                                                | "Danh sách thị trường" | "Lào"             | "Đông Timor"      | 
		| Kiểm tra cập nhật thành công khi nhập các trường bắt buộc                                                                | "Thị trường2"                                                                                          | "Thị trường english2"                                                                                  | "Danh sách thị trường" | "Burundi"         | "Cambodia"        | 
		
		
Scenario Outline:02_[QL Cấu hình danh mục - Sửa] Kiểm tra Sửa cấu hình không thành công 
	Given QLCauHinhDanhMuc-Sua: nhập dữ liệu màn hình Thêm cấu hình với các dữ liệu lỗi sau 
		| Field   | Case   | Name   | NameEnglish   | Type   | CategoryRelevant1   | CategoryRelevant2   | TypeError   | 
		| <field> | <case> | <name> | <nameEnglish> | <type> | <categoryRelevant1> | <categoryRelevant2> | <typeError> |
	When QLCauHinhDanhMuc-Sua: nhấn button Lưu nếu typeError là special thì bỏ qua 
		| TypeError   | 
		| <typeError> |
	And QLCauHinhDanhMuc-Sua: nhấn button OK xác nhận lưu nếu typeError là unique 
		| TypeError   | 
		| <typeError> | 
	Then QLCauHinhDanhMuc-Sua: kiểm tra message error hiển thị 
		| Field   | MessageError   | TypeError   |
		| <field> | <messageError> | <typeError> |
		
	Examples: Thông tin Sửa cấu hình danh mục không thành công 
	
		| field                    | case                           | name          | nameEnglish      | type                   | categoryRelevant1 | categoryRelevant2 | messageError                                 | typeError | 
		| Tên cấu hình             | Kiểm tra bắt buộc              | ""            | "Thị trườngeng3" | "Danh sách thị trường" | "Haiti"           | "Tanzania"        | Trường bắt buộc                              | required  | 
		| Tên cấu hình             | Kiểm tra duy nhất              | "báo cáo"     | "Thị trườngeng3" | "Danh sách thị trường" | "Haiti"           | "Tanzania"        | Tên cấu hình đã tồn tại                      | unique    | 
		| Tên cấu hình             | Kiểm tra nhập ký tự đặc biệt   | "@#$"         | ""               | ""                     | ""                | ""                | Tên cấu hình không chứa ký tự đặc biệt       | special   | 
		| Tên cấu hình (Tiếng Anh  | Kiểm tra bắt buộc              | "Thị trường3" | ""               | "Danh sách thị trường" | "Haiti"           | "Tanzania"        | Trường bắt buộc                              | required  | 
		| Tên cấu hình (Tiếng Anh) | Kiểm tra duy nhất              | "Thị trường3" | "báo cáo eng"    | "Danh sách thị trường" | "Haiti"           | "Tanzania"        | Tên cấu hình tiếng anh đã tồn tại            | unique    | 
		| Tên cấu hình (Tiếng Anh) | Kiểm tra nhập ký tự đặc biệt   | ""            | "@#$"            | ""                     | ""                | ""                | Tên cấu hình không chứa ký tự đặc biệt       | special   | 
		| Danh mục liên quan       | Kiểm tra bắt buộc              | "Thị trường3" | "Thị trườngeng3" | "Danh sách thị trường" | ""                | ""                | Vui lòng chọn ít nhất hai danh mục liên quan |           | 
		| Danh mục liên quan       | Kiểm tra số lượng danh mục < 2 | "Thị trường3" | "Thị trườngeng3" | "Danh sách thị trường" | "Haiti"           | ""                | Vui lòng chọn ít nhất hai danh mục liên quan |           |
		
		
Scenario Outline:03_[QL Cấu hình danh mục - Sửa] Kiểm tra chức năng autocomplete thành công 
	Given QLCauHinhDanhMuc-Sua: nhập dữ liệu trường Loại danh mục là tiền điều kiện nếu field là Danh mục liên quan 
		| Type   | Field   | 
		| <type> | <field> |
	And QLCauHinhDanhMuc-Sua: nhập autocomplete với dữ liệu 
		| Field   | Case   | Autocomplete   | 
		| <field> | <case> | <autocomplete> |
	And QLCauHinhDanhMuc-Sua: lấy danh sách kết quả tìm kiếm UI trả về 
		| Field   | 
		| <field> |
	And QLCauHinhDanhMuc-Sua: lấy danh sách kết quả tìm kiếm DB trả về 
		| Field   | Autocomplete   | Type   | 
		| <field> | <autocomplete> | <type> |
	Then QLCauHinhDanhMuc-Sua: kiểm tra danh sách kết quả tìm kiếm trên UI trả về bằng danh sách kết quả tìm kiếm DB trả về 
	
	Examples: Thông tin kiểm tra chức năng autocomplete 
		| field              | case                                                  | autocomplete      | type         | 
		| Loại danh mục      | Tìm kiếm mặc định                                     | ""                |              | 
		| Loại danh mục      | Tìm kiếm không ra kết quả                             | "@#$"             |              | 
		| Loại danh mục      | Tìm kiếm nhập chuỗi ký tự có space đầu cuối           | "Loại tuyển dụng" |              | 
		| Loại danh mục      | Tìm kiếm tương đối và không phân biệt hoa thường      | "TUYỂN DỤNG"      |              | 
		| Loại danh mục      | Tìm kiếm không phân biệt tiếng việt có dấu, không dấu | "Loai tuyen dung" |              | 
		| Danh mục liên quan | Tìm kiếm mặc định                                     | ""                | "Ngôn ngữ"   | 
		| Danh mục liên quan | Tìm kiếm không ra kết quả                             | "@#$"             | "Ngôn ngữ"   | 
		| Danh mục liên quan | Tìm kiếm nhập chuỗi ký tự có space đầu cuối           | " Tiếng Pháp "    | "Ngôn ngữ"   | 
		| Danh mục liên quan | Tìm kiếm tương đối và không phân biệt hoa thường      | "PHÁP"            | "Ngôn ngữ"   | 
		| Danh mục liên quan | Tìm kiếm không phân biệt tiếng việt có dấu, không dấu | "Tieng Phap"      | "Ngôn ngữ"   |