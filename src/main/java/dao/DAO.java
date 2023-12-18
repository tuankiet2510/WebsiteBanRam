/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.DBConnect;
import entity.Account;
import entity.Cart;
import entity.Review;
import entity.SoLuongDaBan;
import entity.TongChiTieuBanHang;
import entity.Supplier;
//import entity.Account;
import entity.Category;
import entity.Invoice;
import entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public List<Product> getAllProduct() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<SoLuongDaBan> getTop10SanPhamBanChay() {
		List<SoLuongDaBan> list = new ArrayList<>();
		String query = "select top(10) *\r\n" + "from SoLuongDaBan\r\n" + "order by soLuongDaBan desc";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new SoLuongDaBan(rs.getInt(1), rs.getInt(2)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Invoice> getAllInvoice() {
		List<Invoice> list = new ArrayList<>();
		String query = "select * from Invoice";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Invoice(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public int countAllProductBySellID(int sell_ID) {
		String query = "select count(*) from Product where sell_ID=?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, sell_ID);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return 0;
	}

	public int getSellIDByProductID(int productID) {
		String query = "select sell_ID\r\n" + "from Product\r\n" + "where [id]=?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, productID);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return 0;
	}

	public double totalMoneyDay(int day) {
		String query = "select \r\n" + "	SUM(tongGia) \r\n" + "from Invoice\r\n"
				+ "where DATEPART(dw,[ngayXuat]) = ?\r\n" + "Group by ngayXuat ";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, day);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getDouble(1);
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return 0;
	}

	public double totalMoneyMonth(int month) {
		String query = "select SUM(tongGia) from Invoice\r\n" + "where MONTH(ngayXuat)=?\r\n"
				+ "Group by MONTH(ngayXuat)";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, month);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getDouble(1);
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return 0;
	}

	public int countAllProduct() {
		String query = "select count(*) from Product";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return 0;
	}

	public double sumAllInvoice() {
		String query = "select SUM(tongGia) from Invoice";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getDouble(1);
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return 0;
	}

	public int countAllReview() {
		String query = "select count(*) from Review";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return 0;
	}

	public int getCateIDByProductID(String id) {
		String query = "select [cateID] from Product\r\n" + "where [id] =?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return 0;
	}

	public List<Account> getAllAccount() {
		List<Account> list = new ArrayList<>();
		String query = "select * from Account";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Supplier> getAllSupplier() {
		List<Supplier> list = new ArrayList<>();
		String query = "select * from Supplier";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Supplier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<TongChiTieuBanHang> getTop5KhachHang() {
		List<TongChiTieuBanHang> list = new ArrayList<>();
		String query = "select top(5) *\r\n" + "from TongChiTieuBanHang\r\n" + "order by TongChiTieu desc";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new TongChiTieuBanHang(rs.getInt(1), rs.getDouble(2), rs.getDouble(3)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<TongChiTieuBanHang> getTop5NhanVien() {
		List<TongChiTieuBanHang> list = new ArrayList<>();
		String query = "select top(5) *\r\n" + "from TongChiTieuBanHang\r\n" + "order by TongBanHang desc";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new TongChiTieuBanHang(rs.getInt(1), rs.getDouble(2), rs.getDouble(3)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> getTop3() {
		List<Product> list = new ArrayList<>();
		String query = "select top 3 * from Product";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> getNext3Product(int amount) {
		List<Product> list = new ArrayList<>();
		String query = "SELECT *\n" + "  FROM Product\n" + " ORDER BY id\n" + "OFFSET ? ROWS\n"
				+ " FETCH NEXT 3 ROWS ONLY";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> getNext4KingstonProduct(int amount) {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where cateID=1\r\n" + "order by id desc\r\n" + "offset ? rows\r\n"
				+ "fetch next 4 rows only";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> getNext4KingmaxProduct(int amount) {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where cateID=2\r\n" + "order by id desc\r\n" + "offset ? rows\r\n"
				+ "fetch next 4 rows only";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, amount);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> getProductByCID(String cid) {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\n" + "where cateID = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, cid);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> getProductBySellIDAndIndex(int id, int indexPage) {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product \r\n" + "where sell_ID = ?\r\n" + "order by [id]\r\n"
				+ "offset ? rows\r\n" + "fetch next 5 rows only";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2, (indexPage - 1) * 5);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	// test getAllProductAndIndex
	public List<Product> getAllProductAndIndex(int indexPage) {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product \r\n" + "order by [id]\r\n" + "offset ? rows\r\n"
				+ "fetch next 5 rows only";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, (indexPage - 1) * 5);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> getProductByIndex(int indexPage) {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product \r\n" + "order by [id]\r\n" + "offset ? rows\r\n"
				+ "fetch next 9 rows only";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, (indexPage - 1) * 9);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> searchByName(String txtSearch) {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\n" + "where [name] like ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + txtSearch + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Invoice> searchByNgayXuat(String ngayXuat) {
		List<Invoice> list = new ArrayList<>();
		String query = "select * from Invoice\r\n" + "where [ngayXuat] ='" + ngayXuat + "'";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
//            ps.setString(1,ngayXuat);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Invoice(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> searchPriceUnder100() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where [price] < 100";
		try {
			conn = new DBConnect().getConnection(); // mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> searchPrice100To200() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where [price] >= 100 and [price]<=200";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> searchCapacity2GB() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where capacity = '2GB'";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> searchCapacity4GB() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where capacity = '4GB'";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> searchCapacity8GB() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where capacity = '8GB'";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> searchCapacity16GB() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where capacity = '16GB'";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> searchCapacity32GB() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where capacity = '32GB'";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> searchByPriceMinToMax(String priceMin, String priceMax) {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where [price] >= ? and [price]<=?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, priceMin);
			ps.setString(2, priceMax);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> searchPriceAbove200() {
		List<Product> list = new ArrayList<>();
		String query = "select * from Product\r\n" + "where [price] > 200";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> getRelatedProduct(int cateIDProductDetail) {
		List<Product> list = new ArrayList<>();
		String query = "select top 4 * from product\r\n" + "where [cateID] =?\r\n" + "order by id desc";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, cateIDProductDetail);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Review> getAllReviewByProductID(String productId) {
		List<Review> list = new ArrayList<>();
		String query = "select * from Review\r\n" + "where [productID] =?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, productId);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Review(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public Product getProductByID(String id) {
		String query = "select * from Product\n" + "where id = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return null;
	}

	public List<Cart> getCartByAccountID(int accountID) {
		List<Cart> list = new ArrayList<>();
		String query = "select * from Cart where accountID = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public Cart checkCartExist(int accountID, int productID) {

		String query = "select * from Cart\r\n" + "where [accountID] = ? and [productID] = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.setInt(2, productID);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return null;
	}

	public int checkAccountAdmin(int userID) {

		String query = "select isAdmin from Account where [uID]=?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, userID);

			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();

		}
		return 0;
	}

	public int checkAccountSell(int userID) {

		String query = "select isSell from Account where [uID]=?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, userID);

			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();

		}
		return 0;
	}

	public TongChiTieuBanHang checkTongChiTieuBanHangExist(int userID) {

		String query = "select * from TongChiTieuBanHang where [userID]=?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, userID);

			rs = ps.executeQuery();
			while (rs.next()) {
				return new TongChiTieuBanHang(rs.getInt(1), rs.getDouble(2), rs.getDouble(3));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return null;
	}

	public SoLuongDaBan checkSoLuongDaBanExist(int productID) {

		String query = "select * from SoLuongDaBan where productID = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, productID);

			rs = ps.executeQuery();
			while (rs.next()) {
				return new SoLuongDaBan(rs.getInt(1), rs.getInt(2));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return null;
	}

	public List<Category> getAllCategory() {
		List<Category> list = new ArrayList<>();
		String query = "select * from Category";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Category(rs.getInt(1), rs.getString(2)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

//
public List<Product> get8Last() {
    List<Product> list = new ArrayList<>();
    String query = "select top 8 * from Product order by id desc";
    try {
        conn = new DBConnect().getConnection();// mo ket noi voi sql
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
                    rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                    rs.getString(13), rs.getString(14)));
        }
        conn.close();
    } catch(Exception e) { e.printStackTrace();
    }
    return list;
}

	public Product getLast() {
        String query = "select top 1 * from Product order by id desc";
        /*
        try (Connection conn = new DBConnect().getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
    
            if(rs.next()) {
                return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), 
                                   rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), 
                                   rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } */
        try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getString(11), rs.getString(12));
			}
            conn.close();
		} catch(Exception e) { e.printStackTrace(); }
        return null;
    }

	public List<Product> get4KingstonLast() {
		List<Product> list = new ArrayList<>();
		String query = "select top 4 * from Product where cateID = 1 order by id desc";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public List<Product> get4KingmaxLast() {
		List<Product> list = new ArrayList<>();
		String query = "select top 4 * from Product where cateID = 2 order by id desc";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5),
						rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
						rs.getString(13), rs.getString(14)));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return list;
	}

	public Account login(String user, String pass) {
		String query = "select * from Account where [user] = ? and pass = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return null;
	}

	public Account checkAccountExist(String user) {
		String query = "select * from Account where [user] = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return null;
	}

	public Account checkAccountExistByUsernameAndEmail(String username, String email) {
		String query = "select * from Account where [user]=? and [email]=?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getString(6));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return null;
	}

	public Review getNewReview(int accountID, int productID) {
		String query = "select top 1 * from Review where accountID = ? and productID = ? order by maReview desc";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.setInt(2, productID);
			rs = ps.executeQuery();
			while (rs.next()) {
				return new Review(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4));
			}
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
		return null;
	}

	public void singup(String user, String pass, String email) {
		String query = "insert into Account values(?,?,0,0,?)";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.setString(3, email);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void deleteInvoiceByAccountId(String id) {
		String query = "delete from Invoice where [accountID] = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void deleteTongChiTieuBanHangByUserID(String id) {
		String query = "delete from TongChiTieuBanHang\n" + "where [userID] = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void deleteProduct(String pid) {
		String query = "delete from Product where [id] = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, pid);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void deleteProductBySellID(String id) {
		String query = "delete from Product where [sell_ID] = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void deleteCartByAccountID(int accountID) {
		String query = "delete from Cart where [accountID] = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void deleteCartByProductID(String productID) {
		String query = "delete from Cart where [productID]=?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, productID);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void deleteReviewByProductID(String productID) {
		String query = "delete from Review where [productID] = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, productID);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void deleteReviewByAccountID(String id) {
		String query = "delete from Review where [accountID] = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void deleteAccount(String id) {
		String query = "delete from Account where uID = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void deleteSupplier(String idSupplier) {
		String query = "delete from Supplier where idSupplier=?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, idSupplier);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void deleteCart(int productID) {
		String query = "delete from Cart where productID = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, productID);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void insertProduct(String name, String image, String price, String title, String description,
			String category, int sid, String model, String capacity, String delivery, String image2, String image3,
			String image4) {
		String query = "insert into Product([name],[image],[price],[title],[description],[cateID],[sell_ID],[model],[capacity],[delivery],[image2],[image3],[image4]) values(N'" + name + "','" + image + "','"
				+ price + "',N'" + title + "',N'" + description + "','" + category + "','" + sid + "',N'" + model
				+ "',N'" + capacity + "',N'" + delivery + "','" + image2 + "','" + image3 + "','" + image4 + "')";
		try {

			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			/*
			 * ps.setString(1, name); ps.setString(2, image); ps.setString(3, price);
			 * ps.setString(4, title); ps.setString(5, description); ps.setString(6,
			 * category); ps.setInt(7, sid); ps.setString(8, model); ps.setString(9,
			 * capacity); ps.setString(10, delivery); ps.setString(11, image2);
			 * ps.setString(12, image3); ps.setString(13, image4);
			 */
			ps.executeUpdate();
            conn.close();

		} catch (Exception e) { e.printStackTrace();

		}
	}

	public void insertAccount(String user, String pass, String isSell, String isAdmin, String email) {
		String query = "insert into Account([user], pass, isSell, isAdmin, email) values(?,?,?,?,?)";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.setString(3, isSell);
			ps.setString(4, isAdmin);
			ps.setString(5, email);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void insertTongChiTieuBanHang(int userID, double tongChiTieu, double tongBanHang) {
		String query = "insert into TongChiTieuBanHang(userID,TongChiTieu,TongBanHang) values(?,?,?)";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, userID);
			ps.setDouble(2, tongChiTieu);
			ps.setDouble(3, tongBanHang);

			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void insertSoLuongDaBan(int productID, int soLuongDaBan) {
		String query = "insert into SoLuongDaBan(productID,soLuongDaBan) values(?,?)";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, productID);
			ps.setInt(2, soLuongDaBan);

			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void insertSupplier(String nameSupplier, String phoneSupplier, String emailSupplier, String addressSupplier,
			String cateID) {
		String query = "insert into Supplier(nameSupplier, phoneSupplier, emailSupplier, addressSupplier, cateID) values(?,?,?,?,?)";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, nameSupplier);
			ps.setString(2, phoneSupplier);
			ps.setString(3, emailSupplier);
			ps.setString(4, addressSupplier);
			ps.setString(5, cateID);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	private static java.sql.Date getCurrentDate() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}

	public void insertReview(int accountID, int productID, String contentReview) {
		String query = "insert into Review(accountID, productID, contentReview, dateReview) values(?,?,?,?)";

		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.setInt(2, productID);
			ps.setString(3, contentReview);
			ps.setDate(4, getCurrentDate());
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void insertInvoice(int accountID, double tongGia) {
		String query = "insert into Invoice(accountID,tongGia,ngayXuat) values(?,?,?)";

		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.setDouble(2, tongGia);
			ps.setDate(3, getCurrentDate());
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();

		}
	}

	public void insertCart(int accountID, int productID, int amount, String made_in) {
		String query = "insert into Cart(accountID, productID, amount, made_in) values(?,?,?,?)";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.setInt(2, productID);
			ps.setInt(3, amount);
			ps.setString(4, made_in);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void editProduct(String pname, String pimage, String pprice, String ptitle, String pdescription,
			String pcategory, String pmodel, String pcapacity, String pdelivery, String pimage2, String pimage3,
			String pimage4, String pid) {
		String query = "update Product set [name] = ?,[image] = ?, price = ?, title = ?, [description] = ?,cateID = ?,model= ?,capacity= ?,delivery=?, image2=?,image3=?,image4=? where [id] = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, pname);
			ps.setString(2, pimage);
			ps.setString(3, pprice);
			ps.setString(4, ptitle);
			ps.setString(5, pdescription);
			ps.setString(6, pcategory);
			ps.setString(7, pmodel);
			ps.setString(8, pcapacity);
			ps.setString(9, pdelivery);
			ps.setString(10, pimage2);
			ps.setString(11, pimage3);
			ps.setString(12, pimage4);
			ps.setString(13, pid);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();

		}
	}

	public void editProfile(String username, String password, String email, int uID) {
		String query = "update Account set [user]=?,[pass]=?,[email]=? where [uID] = ?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.setInt(4, uID);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void editTongChiTieu(int accountID, double totalMoneyVAT) {
		String query = "exec dbo.proc_CapNhatTongChiTieu ?,?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, accountID);
			ps.setDouble(2, totalMoneyVAT);

			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();

		}
	}

	public void editSoLuongDaBan(int productID, int soLuongBanThem) {
		String query = "exec dbo.proc_CapNhatSoLuongDaBan ?,?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, productID);
			ps.setInt(2, soLuongBanThem);

			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();

		}
	}

	public void editTongBanHang(int sell_ID, double tongTienBanHangThem) {
		String query = "exec dbo.proc_CapNhatTongBanHang ?,?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, sell_ID);
			ps.setDouble(2, tongTienBanHangThem);

			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();

		}
	}

	public void editAmountAndMadeInCart(int accountID, int productID, int amount, String made_in) {
		String query = "update into Cart set [amount]= ? ,[made_in]= ? where [accountID]= ? and [productID]=?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, amount);
			ps.setString(2, made_in);
			ps.setInt(3, accountID);
			ps.setInt(4, productID);
			ps.executeUpdate();
            conn.close();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public void editAmountCart(int accountID, int productID, int amount) {
		String query = "update into Cart set [amount]= ? where [accountID]=? and [productID]=?";
		try {
			conn = new DBConnect().getConnection();// mo ket noi voi sql
			ps = conn.prepareStatement(query);
			ps.setInt(1, amount);
			ps.setInt(2, accountID);
			ps.setInt(3, productID);
			ps.executeUpdate();
		} catch (Exception e) { e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		DAO dao = new DAO();

//        List<Invoice> list = dao.searchByNgayXuat("2021-11-20");
//        for (Invoice o : list) 
//        { 
//        	System.out.println(o); 
//        }
//      int s = dao.checkAccountAdmin(1);
//      System.out.println(s);
//      System.out.println("da chay xong");

		/*
		 * for (Review o : list) { System.out.println(o); }
		 */
	}

}
