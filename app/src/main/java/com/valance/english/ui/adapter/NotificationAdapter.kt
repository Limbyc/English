import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valance.english.R
import com.valance.english.db.RegistrationInfo

class NotificationAdapter(private val context: Context, private val data: List<RegistrationInfo>) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.notification_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val registrationInfo = data[position]

        holder.usernameTextView.text = registrationInfo.username
        holder.timeTextView.text = registrationInfo.description
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usernameTextView: TextView = itemView.findViewById(R.id.notification)
        val timeTextView: TextView = itemView.findViewById(R.id.description)
    }
}
