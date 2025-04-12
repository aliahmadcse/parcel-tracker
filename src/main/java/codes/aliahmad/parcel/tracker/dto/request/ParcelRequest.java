package codes.aliahmad.parcel.tracker.dto.request;


public record ParcelRequest(String senderEmail,
                            String receiverEmail,
                            String deliveryAddress)
{
}
