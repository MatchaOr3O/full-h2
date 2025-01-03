import axios from 'axios';


const PRODUCT_API_URL = "http://localhost:8080/api/products";
class ProductService{
    getProducts(){
        return axios.get(PRODUCT_API_URL); //get 段連結
    }

    getProductById(productId){
        return axios.get(`${PRODUCT_API_URL}/${productId}`);
    }

    deleteProduct(productId){
        return axios.delete(`${PRODUCT_API_URL}/${productId}`);
    }

    createProduct(product){
        return axios.post(PRODUCT_API_URL, product); // 通知後面函式庫的要求
    }

    updateProduct(product, productId){
        return axios.put(`${PRODUCT_API_URL}/${productId}`, product);
    }
}

export default new ProductService();