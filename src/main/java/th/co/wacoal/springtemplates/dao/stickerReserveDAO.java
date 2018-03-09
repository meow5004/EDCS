/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.wacoal.springtemplates.dao;

import java.util.List;
import th.co.wacoal.springtemplates.domain.StickerReserve;

/**
 *
 * @author admin
 */
public interface stickerReserveDAO {

    public List<StickerReserve> findStickersByUserId(String userId);

    public void clearStickerByUserId(String userId);

    public void addStickerToReserve(StickerReserve sticker);

    public void addStickersToReserve(List<StickerReserve> stickers);

    public void deleteSticker(Integer id);

}
